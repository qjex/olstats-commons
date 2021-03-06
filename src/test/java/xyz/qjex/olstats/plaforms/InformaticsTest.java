package xyz.qjex.olstats.plaforms;

import org.junit.Test;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by qjex on 8/20/16.
 */
public class InformaticsTest {

    @Test
    public void getAllSubmissions() throws Exception {
        Platform inf = new Informatics();
        Map<String, String> ids = new HashMap<>();
        User qjexUser = new User("qjex", ids);
        ids.put(inf.getIdDescriptor(), "");
        List<Submission> submissions = inf.getAllSubmissions(qjexUser);
        assertNull(submissions);

        ids.put(inf.getIdDescriptor(), "57571");
        qjexUser.setIds(ids);
        submissions = inf.getAllSubmissions(qjexUser);
        assertNotNull(submissions);
        assertTrue(submissions.size() != 0);
    }

}