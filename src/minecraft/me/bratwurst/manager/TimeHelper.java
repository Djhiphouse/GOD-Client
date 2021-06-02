package me.bratwurst.manager;

public class TimeHelper {
    private static long lastMS = 0L;

    public boolean isDelayComplete(float f) {
        if(System.currentTimeMillis() - this.lastMS >= f){
            return true;
        }
        return false;
    }

    public static long getCurrentMS(int i) {
        return System.nanoTime() /1000000;
    }

    public void setLastMS(long lastMS) {
        this.lastMS = System.currentTimeMillis();
    }
    public int convertToMS(int perSecond) {
        return 1000 / perSecond;
    }
    public static boolean hasReached(long milliseconds) {
        return getCurrentMS(4000) - lastMS >= milliseconds;
    }
    public static void reset() {
        lastMS = getCurrentMS(4000);
    }

}