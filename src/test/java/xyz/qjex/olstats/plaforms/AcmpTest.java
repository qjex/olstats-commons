package xyz.qjex.olstats.plaforms;

import org.junit.Test;
import xyz.qjex.olstats.entity.Submission;
import xyz.qjex.olstats.entity.User;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by qjex on 8/25/16.
 */
public class AcmpTest {
    @Test
    public void getAllSubmissions() throws Exception {
        Platform inf = new Acmp();
        Map<String, String> ids = new HashMap<>();
        User qjexUser = new User("qjex", ids);
        ids.put(inf.getIdDescriptor(), "");
        List<Submission> submissions = inf.getAllSubmissions(qjexUser);
        assertNull(submissions);

        ids.put(inf.getIdDescriptor(), "92630");
        qjexUser.setIds(ids);
        submissions = inf.getAllSubmissions(qjexUser);
        assertNotNull(submissions);
        assertTrue(submissions.size() != 0);
        int cnt = 0;
        Set<String> tasks = new TreeSet<>();
        for (Submission submission : submissions) {
            if (submission.getDate() < 1472137599) {
                if (tasks.contains(submission.getTaskName())) continue;
                tasks.add(submission.getTaskName());
                cnt++;
            }
        }

        assertTrue(cnt == 174);
    }

}