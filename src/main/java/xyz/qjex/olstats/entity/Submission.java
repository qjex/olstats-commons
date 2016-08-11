package xyz.qjex.olstats.entity;

/**
 * Created by qjex on 8/9/16.
 */
public class Submission {

    private String platformName;
    private String internalId;
    private long date;
    private String user;
    private String taskName;

    public Submission(String platformName, String internalId, String taskName, long date, String user) {
        this.platformName = platformName;
        this.date = date;
        this.internalId = internalId;
        this.user = user;
        this.taskName = taskName;
    }

    public long getDate() {
        return date;
    }

    public String getInternalId() {
        return internalId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public String getUser() {
        return user;
    }

    public String getTaskName() {
        return taskName;
    }
}
