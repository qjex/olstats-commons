package xyz.qjex.olstats.entity;

/**
 * Created by qjex on 8/9/16.
 */
public class Submission {

    private String platformName;
    private String internalId;
    private long date;
    private String userId;
    private String taskName;

    public Submission(String platformName, String internalId, String taskName, long date, String userId) {
        this.platformName = platformName;
        this.date = date;
        this.internalId = internalId;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String toString() {
        return platformName + ": " + internalId + " `" + taskName + "` " + userId;
    }
}
