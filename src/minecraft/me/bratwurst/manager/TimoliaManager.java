package me.bratwurst.manager;

import java.util.HashSet;

public class TimoliaManager {
    private final HashSet<String> hashSet = new HashSet<>();
    private static final TimoliaManager instance = new TimoliaManager();

    public static TimoliaManager getInstance() {
        return instance;
    }

    private TimoliaManager() {

    }

    public void addtarget(String name) {
        hashSet.add(name);
    }

    public boolean istarget(String name) {
        return hashSet.contains(name);
    }

    public void removetarget(String name) {
        hashSet.remove(name);
    }

    public HashSet<String> getHashSet() {
        return hashSet;
    }
}
