package xyz.qjex.olstats.entity;

/**
 * Created by qjex on 8/9/16.
 */
public class AcmpSubmission {
    private String id;
    private long date;
    private String taskName;

    public AcmpSubmission(String id, String taskName, long date) {
        this.id = id;
        this.date = date;
        this.taskName = taskName;
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
}
