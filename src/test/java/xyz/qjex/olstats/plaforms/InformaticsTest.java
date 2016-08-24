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
        ids.put(inf.getIdDescriptor(), "57571");
        User qjexUser = new User("0", "qjex", ids, 0L);
        List<Submission> submissions = inf.getAllSubmissions(qjexUser);
        assertNotNull(submissions);
        assertTrue(submissions.size() != 0);
    }

}