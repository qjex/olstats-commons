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
            logger.warn("Can't get acmp id for user " + user.getUserId() + "(" + user.getName() + ")");
            return null;
        }
        List<AcmpSubmission> acmpSubmissions = new AcmpParser().getStatus(acmpId);
        if (acmpSubmissions == null) {
            logger.warn("Can't get submissions on acmp for user " + user.getUserId() + " (" + acmpId +  ")");
            return null;
        }
        List<Submission> result = new ArrayList<>();
        for (AcmpSubmission submission : acmpSubmissions) {
            result.add(new Submission(getName(), submission.getId(), submission.getTaskName(), submission.getDate(), user.getUserId()));
        }
        return result;
    }

    @Override
    public String getName() {
        return "acmp";
    }

    @Override
    public String getIdDescriptor() {
        return "acmpId";
    }
}
