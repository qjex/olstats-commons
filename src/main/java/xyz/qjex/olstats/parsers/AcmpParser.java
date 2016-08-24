package xyz.qjex.olstats.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.qjex.olstats.entity.AcmpSubmission;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Created by qjex on 8/9/16.
 */
public class AcmpParser {

    private static final Logger logger = LogManager.getLogger();

    public List<AcmpSubmission> getStatus(String id) {
        String pattern = "http://acmp.ru/index.asp?main=status&id_mem=%s&id_res=1&id_t=0&page=%d";

        int page = 0;
        List<AcmpSubmission> result = new ArrayList<>();
        while (true) {
            StringBuilder query = new StringBuilder();
            new Formatter(query).format(pattern, id, page);

            try {
                Document doc = Jsoup.connect(query.toString()).get();

                if (pageEmpty(doc)) break;

                result.addAll(parse(doc));
                page++;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (RuntimeException e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }

    private boolean pageEmpty(Document doc) throws RuntimeException {
        Elements els = doc.getElementsByClass("btext");
        if (els.isEmpty()) throw new RuntimeException("no btext classes in document");

        Element pageNumberElement = els.first();
        String pageNumber = pageNumberElement.text();
        try {
            int pos = pageNumber.indexOf('-');
            String first = pageNumber.substring(1, pos - 1);
            String second = pageNumber.substring(pos + 3);

            long l = Long.valueOf(first);
            long r = Long.valueOf(second);

            return l > r;

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new RuntimeException("can't parse interval on page");
        }
    }

    private List<AcmpSubmission> parse(Document doc) throws RuntimeException {
        List<AcmpSubmission> submissions = new ArrayList<>();
        try {
            Elements rows = doc.getElementsByClass("white");
            for (Element row : rows) {
                Elements cols = row.select("td");

                String id = cols.get(0).text();
                String unparsedDate = cols.get(1).text();
                String taskName = cols.get(3).text();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy H:mm:ss");

                LocalDateTime dateTime = LocalDateTime.parse(unparsedDate, formatter);
                long date = dateTime.atZone(ZoneId.of("Europe/Moscow")).toEpochSecond();

                submissions.add(new AcmpSubmission(id, taskName, date));
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
            throw new RuntimeException("can't parse table");
        }
        return submissions;
    }

}
