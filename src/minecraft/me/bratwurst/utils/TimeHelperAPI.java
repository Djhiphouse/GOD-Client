package me.bratwurst.utils;


public class TimeHelperAPI {
    private static long lastTime = getCurrentMs();

    public TimeHelperAPI() {
        reset();
    }

    public static long getCurrentMs() {
        return System.currentTimeMillis();
    }

    public long getLastTime() {
        return lastTime;
    }

    public static long getDifference() {
        return getCurrentMs() - lastTime;
    }

    public static void reset() {
        lastTime = getCurrentMs();
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