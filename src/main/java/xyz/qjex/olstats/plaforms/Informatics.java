package xyz.qjex.olstats.plaforms;

import xyz.qjex.olstats.entity.InformaticsSubmission;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;
import xyz.qjex.olstats.parsers.InformaticsParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjex on 8/20/16.
 */
public class Informatics implements Platform {

    @Override
    public List<Submission> getAllSubmissions(User user) {
        String handle = user.getId(getIdDescriptor());
        if (handle == null) {
            throw new IllegalAccessError("can't get user handle");
        }
        List<InformaticsSubmission> submissions = new InformaticsParser().getStatus(handle);
        if (submissions == null) {
            return null;
        }

        List<Submission> result = new ArrayList<>();
        for (InformaticsSubmission submission : submissions) {
            if (!submission.getVerdict().equalsIgnoreCase("ok")) continue;
            result.add(new Submission(getName(), submission.getId(), submission.getTaskName(), submission.getDate(),
                    handle));
        }
        return result;
    }

    @Override
    public String getName() {
        return "informatics";
    }

    @Override
    public String getIdDescriptor() {
        return "informatics";
    }

    @Override
    public String getSiteName() {
        return "informatics.mccme.ru";
    }

    @Override
    public String getSiteAddress() {
        return "http://informatics.mccme.ru/";
    }
}
