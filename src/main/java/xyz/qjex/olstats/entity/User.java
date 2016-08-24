package xyz.qjex.olstats.entity;

import java.util.Map;

/**
 * Created by qjex on 8/9/16.
 */
public class User {

    private String name;
    private Map<String, String> ids;
    private long lastUpdate;

    public User(String name, Map<String, String> ids, Long lastUpdate) {
        this.ids = ids;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getIds() {
        return ids;
    }

    public String getId(String descriptor) {
        return ids.get(descriptor);
    }

    public void setId(String descriptor, String id) {
        ids.put(descriptor, id);
    }

}
