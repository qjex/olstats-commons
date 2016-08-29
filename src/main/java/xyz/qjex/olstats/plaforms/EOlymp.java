package xyz.qjex.olstats.plaforms;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by star_orpheus on 8/28/16.
 */
public class EOlymp implements Platform {

    @Override
    public List<Submission> getAllSubmissions(User user) {
        List<Submission> res = new ArrayList<>();
        String nick = user.getId(getIdDescriptor());

        try {
            for (int page = 0; page <= 1000; page++) {
                String url = "http://www.e-olymp.com/en/users/" + nick + "/submissions?page=" + page;

                Document doc = Jsoup.connect(url).validateTLSCertificates(false).get();
                Elements tbody;

                try {
                    tbody = doc.body().getElementsByTag("tbody").get(0).getElementsByTag("tr");
                } catch (IndexOutOfBoundsException e) {
                    break;
                }

                for (Element subm : tbody) {
                    boolean ac = false;

                    for (Element elem : subm.getElementsByTag("b")) {
                        if (elem.text().equalsIgnoreCase("Accepted")) {
                            ac = true;
                        }
                    }
                    if (!ac) continue;
                    Submission sub = parseLine(subm.getAllElements(), nick);
                    if (sub != null) res.add(sub);
                }
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private long parseDate(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm:ss a");
        LocalDateTime dateTime = LocalDateTime.parse(text, formatter);
        return dateTime.atZone(ZoneId.of("Europe/Moscow")).toEpochSecond();
    }

    private Submission parseLine(Elements el, String user) {
        String internalId = "-1";
        for (Element intr : el.select("a.text-muted"))
            internalId = intr.text();
        if (internalId.compareTo("-1") == 0)
            return null;

        String taskName = "-1";
        for (Element intr : el.select("td[style=\"max-width:180px\"]"))
            for (Element intrr : intr.select("a[href]"))
                taskName = intrr.text();
        if (taskName.compareTo("-1") == 0)
            return null;

        String unparsedDate = "-1";
        for (Element intr : el.select("td[class='text-muted mdl-data-table__cell--non-numeric']"))
            unparsedDate = intr.text();
        if (unparsedDate.compareTo("-1") == 0)
            return null;

        long date = parseDate(unparsedDate);

        return new Submission(getName(), internalId, taskName, date, user);
    }


    @Override
    public String getName() {
        return "E-Olymp";
    }

    @Override
    public String getIdDescriptor() {
        return "E-Olymp";
    }

    @Override
    public String getSiteName() {
        return "e-olymp.com";
    }

    @Override
    public String getSiteAddress() {
        return "https://www.e-olymp.com/";
    }

}
