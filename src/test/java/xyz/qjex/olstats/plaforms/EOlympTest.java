package xyz.qjex.olstats.plaforms;

import org.junit.Test;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by qjex on 8/29/16.
 */
public class EOlympTest {
    @Test
    public void getAllSubmissions() throws Exception {
        Platform eOlymp = new EOlymp();
        Map<String, String> ids = new HashMap<>();
        User qjexUser = new User("qjex", ids);
        ids.put(eOlymp.getIdDescriptor(), "");
        List<Submission> submissions = eOlymp.getAllSubmissions(qjexUser);
        assertNull(submissions);

        ids.put(eOlymp.getIdDescriptor(), "andrew2509");
        qjexUser.setIds(ids);
        submissions = eOlymp.getAllSubmissions(qjexUser);
        assertNotNull(submissions);
        assertTrue(submissions.size() != 0);
    }

}