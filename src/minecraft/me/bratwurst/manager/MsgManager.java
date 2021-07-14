package me.bratwurst.manager;

import java.util.HashSet;

public class MsgManager {
    private final HashSet<String> hashSet = new HashSet<>();
    private static final MsgManager instance = new MsgManager();

    public static MsgManager getInstance() {
        return instance;
    }

    private MsgManager() {

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
