package xyz.qjex.olstats.plaforms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.qjex.cfutils.methods.ApiMethods;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjex on 8/9/16.
 */
public class Codeforces implements Platform{

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<Submission> getAllSubmissions(User user) {
        String handle = user.getId(getIdDescriptor());
        if (handle == null) {
            logger.warn("Can't get codeforces handle for user " + user.getUserId() + "(" + user.getName() + ")");
            return null;
        }
        try {
            List<xyz.qjex.cfutils.Submission> submissions = ApiMethods.getUserStatus(new xyz.qjex.cfutils.User(handle));
            List<Submission> result = new ArrayList<>();
            for (xyz.qjex.cfutils.Submission submission: submissions) {
                result.add(new Submission(getName(), String.valueOf(submission.getId()),
                        submission.getProblem().getUniqueName(), submission.getCreationTimeSeconds(),
                        user.getUserId()));
            }
            return result;
        } catch (IOException e) {
            logger.warn("Can't get submissions on cf for user " + user.getUserId() + " (" + handle +  ")");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getName() {
        return "cf";
    }

    @Override
    public String getIdDescriptor() {
        return "cfHandle";
    }

    @Override
    public String getSiteName() {
        return "codeforces.com/";
    }

    @Override
    public String getSiteAddress() {
        return "http://codeforces.com/";
    }
}
