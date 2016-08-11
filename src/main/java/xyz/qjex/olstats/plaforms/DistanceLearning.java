package xyz.qjex.olstats.plaforms;

import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.util.List;

/**
 * Created by qjex on 8/9/16.
 */
public class DistanceLearning implements Platform {
    @Override
    public List<Submission> getAllSubmissions(User user) {
        return null;
    }

    @Override
    public String getName() {
        return "dl";
    }

    @Override
    public String getIdDescriptor() {
        return "dlId";
    }
}
