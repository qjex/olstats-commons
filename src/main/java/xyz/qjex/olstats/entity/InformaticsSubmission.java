package xyz.qjex.olstats.entity;

/**
 * Created by qjex on 8/20/16.
 */
public class InformaticsSubmission {

    private String id;
    private long date;
    private String taskName;
    private String verdict;

    public InformaticsSubmission(String id, String taskName, long date, String verdict) {
        this.id = id;
        this.date = date;
        this.taskName = taskName;
        this.verdict = verdict;
    }

    public long getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getVerdict() {
        return verdict;
    }
}
