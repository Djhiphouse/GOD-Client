package me.bratwurst.utils;

public class Time {
    private long time = Time.getCurrentTime();

    public static long getCurrentTime() {
        return System.nanoTime() / 1000000L;
    }

    public boolean passed(long time) {
        return this.getTime() > time;
    }

    public void reset() {
        this.time = Time.getCurrentTime();
    }

    public long getTime() {
        return Time.getCurrentTime() - this.time;
    }
}

