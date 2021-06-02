package me.pseey.AltManager;

import java.util.ArrayList;
import java.util.List;

public class AltManager {
    public static Alt lastAlt;
    public static ArrayList<Alt> registry;

    public ArrayList<Alt> getRegistry() {
        return AltManager.registry;
    }

    public void setLastAlt(final Alt alt) {
        AltManager.lastAlt = alt;
    }

    static {
        AltManager.registry = new ArrayList<Alt>();
    }
    
    public List<Alt> getAlts() {
        return this.registry;
      }
}
