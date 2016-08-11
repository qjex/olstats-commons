package xyz.qjex.olstats.plaforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qjex on 8/9/16.
 */
public class PlatformsBuilder {

    private List<Platform> platforms;
    private Map<String, Platform> names;

    public PlatformsBuilder() {
        this.platforms = new ArrayList<>();
        names = new HashMap<>();
    }

    public Platforms build() {
        return new Platforms(platforms, names);
    }

    public void addPlatform(Platform platform) {
        platforms.add(platform);
        names.put(platform.getName(), platform);
    }

}
