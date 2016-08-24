package xyz.qjex.olstats.plaforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.qjex.olstats.entity.AcmpSubmission;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;
import xyz.qjex.olstats.parsers.AcmpParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjex on 8/9/16.
 */
public class Acmp implements Platform {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Submission> getAllSubmissions(User user) {
        String acmpId = user.getId(getIdDescriptor());
        if (acmpId == null) {
            throw new IllegalAccessError("can't get user handle");
        }
        List<AcmpSubmission> acmpSubmissions = new AcmpParser().getStatus(acmpId);
        if (acmpSubmissions == null) {
            return null;
        }
        List<Submission> result = new ArrayList<>();
        for (AcmpSubmission submission : acmpSubmissions) {
            result.add(new Submission(getName(), submission.getId(), submission.getTaskName(), submission.getDate(), acmpId));
        }
        return result;
    }

    @Override
    public String getName() {
        return "acmp";
    }

    @Override
    public String getIdDescriptor() {
        return "acmp";
    }

    @Override
    public String getSiteName() {
        return "acmp.ru";
    }

    @Override
    public String getSiteAddress() {
        return "https://acmp.ru";
    }

}
