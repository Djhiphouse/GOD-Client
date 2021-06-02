package me.bratwurst.manager;

import java.util.HashSet;

public class FreundManager {
    private final HashSet<String> hashSet = new HashSet<>();
    private static final FreundManager instance = new FreundManager();

    public static FreundManager getInstance() {
        return instance;
    }

    private FreundManager() {

    }

    public void addFriend(String name) {
        hashSet.add(name);
    }

    public boolean isFriend(String name) {
        return hashSet.contains(name);
    }

    public void removeFriend(String name) {
        hashSet.remove(name);
    }

    public HashSet<String> getHashSet() {
        return hashSet;
    }
}
