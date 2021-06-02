package me.bratwurst.utils;

public class GoodTimeHelper {
    private long lastTime = getCurrentMs();

    public GoodTimeHelper() {
        reset();
    }

    public long getCurrentMs() {
        return System.currentTimeMillis();
    }

    public long getLastTime() {
        return lastTime;
    }

    public long getDifference() {
        return getCurrentMs() - lastTime;
    }

    public void reset() {
        lastTime = getCurrentMs();
    }

    public int convertToMs(int paramInt) {
        return -paramInt * 50;
    }

    public boolean hasPassed(long milliseconds) {
        return getDifference() >= milliseconds;
    }

    public boolean hasReached(long milliseconds) {
        return getDifference() >= milliseconds;
    }
}
