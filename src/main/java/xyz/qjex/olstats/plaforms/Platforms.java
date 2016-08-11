package xyz.qjex.olstats.plaforms;

import java.util.List;
import java.util.Map;

/**
 * Created by qjex on 8/9/16.
 */
public class Platforms {

    private List<Platform> platforms;
    private Map<String, Platform> names;

    public Platforms(List<Platform> platforms, Map<String, Platform> names) {
        this.platforms = platforms;
        this.names = names;
    }

    public Platform getByName(String name) {
        return names.get(name);
    }

    public List<Platform> getAll() {
        return platforms;
    }
}
