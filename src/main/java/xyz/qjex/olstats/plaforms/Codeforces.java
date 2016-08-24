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
            throw new IllegalAccessError("can't get user handle");
        }
        try {
            List<xyz.qjex.cfutils.Submission> submissions = ApiMethods.getUserStatus(new xyz.qjex.cfutils.User(handle));
            List<Submission> result = new ArrayList<>();
            for (xyz.qjex.cfutils.Submission submission: submissions) {
                if (!submission.getVerdict().equalsIgnoreCase("ok")) continue;
                result.add(new Submission(getName(), String.valueOf(submission.getId()),
                        submission.getProblem().getUniqueName(), submission.getCreationTimeSeconds(),
                        handle));
            }
            return result;
        } catch (IOException e) {
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
        return "cf";
    }

    @Override
    public String getSiteName() {
        return "codeforces.com";
    }

    @Override
    public String getSiteAddress() {
        return "http://codeforces.com/";
    }
}
