package xyz.qjex.olstats.parsers;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.qjex.olstats.entity.AcmpSubmission;
import xyz.qjex.olstats.entity.InformaticsSubmission;
import xyz.qjex.olstats.entity.Submission;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Created by qjex on 8/20/16.
 */
public class InformaticsParser {

    public List<InformaticsSubmission> getStatus(String id) {

        String pattern = "http://informatics.mccme.ru/moodle/ajax/ajax.php?problem_id=0&group_id=0" +
                "&user_id=%s&lang_id=-1&status_id=-1&statement_id=0&objectName=submits" +
                "&count=1000000000&with_comment=&page=0&action=getHTMLTable";

        List<InformaticsSubmission> result;
        StringBuilder query = new StringBuilder();
        new Formatter(query).format(pattern, id);
        try {
            String data = IOUtils.toString(new URL(query.toString()), Charset.forName("UTF-8"));
            String html = prepare(data);
            Document doc = Jsoup.parse(html);
            result = parse(doc);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    private String prepare(String data) {
        data = StringEscapeUtils.unescapeJava(data);
        data = data.substring(data.indexOf("<table"), data.length() - 3);
        data = data.replace("\n", "");
        data = "<html>" + data + "</html>";
        return data;
    }


    private List<InformaticsSubmission> parse(Document doc) throws RuntimeException {
        List<InformaticsSubmission> submissions = new ArrayList<>();
        try {
            Elements rows = doc.getElementsByTag("tr");
            if (rows.size() == 0) throw new IllegalStateException("can't find table");
            for (Element row : rows) {
                if (row.hasClass("Caption")) continue;;
                Elements cols = row.select("td");

                String id = cols.get(0).text();
                String taskName = cols.get(2).child(0).text();
                String unparsedDate = cols.get(3).text();
                String verdict = cols.get(5).text().trim().toLowerCase();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");

                LocalDateTime dateTime = LocalDateTime.parse(unparsedDate, formatter);
                long date = dateTime.atZone(ZoneId.of("Europe/Moscow")).toEpochSecond();

                submissions.add(new InformaticsSubmission(id, taskName, date, verdict));
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new RuntimeException("can't parse table");
        }
        return submissions;
    }
}
