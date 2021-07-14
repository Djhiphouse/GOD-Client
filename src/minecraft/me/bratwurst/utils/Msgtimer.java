package me.bratwurst.utils;

public class Msgtimer {
    private static long lastTime = msgMs();

    public Msgtimer() {
        reset();
    }

    public static long msgMs() {
        return System.currentTimeMillis();
    }

    public long getLastTime() {
        return lastTime;
    }

    public static long getDifference() {
        return msgMs() - lastTime;
    }

    public static void reset() {
        lastTime = msgMs();
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