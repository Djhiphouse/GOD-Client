package me.pseey.utils;

public class TimeHelper {
    private static long lastTime = getCurrentTime();

    public TimeHelper() {
        reset();
    }

    public static long getCurrentTime() {
        return System.nanoTime() / 1000000;
    }

    public long getLastTime() {
        return lastTime;
    }

    public static long getDifference() {
        return getCurrentTime() - lastTime;
    }

    public static void reset() {
        lastTime = getCurrentTime();
    }

    public int convertToMs(int paramInt) {
        return -paramInt * 50;
    }

    public static boolean hasPassed(long milliseconds) {
        return getDifference() >= milliseconds;
    }

    public static boolean hasReached(long milliseconds) {
        return getDifference() >= milliseconds;
    }
}