package xyz.qjex.olstats.plaforms;

import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.util.List;

/**
 * Created by qjex on 8/9/16.
 */
public interface Platform {

    List<Submission> getAllSubmissions(User user);

    String getName();

    String getIdDescriptor();

    String getSiteName();

    String getSiteAddress();

}
