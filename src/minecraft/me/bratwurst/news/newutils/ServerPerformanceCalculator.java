package me.bratwurst.news.newutils;

import java.util.ArrayList;
import java.util.Arrays;

public class ServerPerformanceCalculator {
    public static ArrayList<Long> times = new ArrayList();
    public static ArrayList<Double> tpsList = new ArrayList();
    public static long currentLag;
    public static double lastTps;
    public static TimeHelper timeUtils;

    public static void calculate() {
        ServerPerformanceCalculator.calculateLag();
        ServerPerformanceCalculator.calculateTps();
    }

    public static String getFormatLag() {
        long clearLag = currentLag - System.currentTimeMillis();
        if (System.currentTimeMillis() - currentLag > 2500L && System.currentTimeMillis() - currentLag < 500000L) {
            String returnResult = String.valueOf(clearLag).replace("-", "");
            lastTps = 0.0;
            return returnResult + "ms";
        }
        return "";
    }

    private static void calculateLag() {
        currentLag = System.currentTimeMillis();
    }

    public static String getFormatTps() {
        String fm = String.format("%.3f", lastTps);
        if (fm.startsWith(".")) {
            fm = "0" + fm;
        }
        double tpsPercentage = lastTps / 20.0 * 100.0;
        Arrays.sort(tpsList.toArray());
        double median = tpsList.toArray().length % 2 == 0 ? ((Double)tpsList.toArray()[tpsList.toArray().length / 2] + (Double)tpsList.toArray()[tpsList.toArray().length / 2 - 1]) / 2.0 : (Double)tpsList.toArray()[tpsList.toArray().length / 2];
        double defaultdifPercentage = median / 20.0 * 100.0;
        if (System.currentTimeMillis() - currentLag > 2500L && System.currentTimeMillis() - currentLag < 500000L) {
            return "\u00a740,000";
        }
        return MiscUtil.getTPSColor(lastTps) + fm + " \u00a77(\u00a7e\u00b1" + MiscUtil.getTPSColor(median) + String.format("%.3f", 20.0 - median) + "\u00a77) (" + MiscUtil.getTPSColorByProzent(tpsPercentage) + String.format("%.3f", tpsPercentage) + "\u00a77/" + MiscUtil.getTPSColorByProzent(100.0 - tpsPercentage) + String.format("%.3f", 100.0 - tpsPercentage) + "\u00a7e\u00a7l%\u00a77)";
    }

    private static void calculateTps() {
        times.add(Math.max(1000L, timeUtils.getDelay()));
        long timesAdded = 0L;
        if (times.size() > 5) {
            times.remove(0);
        }
        for (long l : times) {
            timesAdded += l;
        }
        long roundedTps = timesAdded / (long)times.size();
        lastTps = System.currentTimeMillis() - currentLag > 2500L && System.currentTimeMillis() - currentLag < 500000L ? 0.0 : 20.0 / (double)roundedTps * 1000.0;
        tpsList.add(lastTps);
        timeUtils.reset();
    }

    static {
        timeUtils = new TimeHelper();
    }

    public static class TimeHelper {
        private long lastMS = 0L;

        public TimeHelper() {
            this.reset();
        }

        public int convertToMS(int d) {
            return 1000 / d;
        }

        public long getDelay() {
            return System.currentTimeMillis() - this.lastMS;
        }

        public boolean hasTimeReached(long delay) {
            return this.getDelay() >= delay;
        }

        public void reset() {
            this.setLastMS(System.currentTimeMillis());
        }

        public void setLastMS(long lastMS) {
            this.lastMS = lastMS;
        }
    }
}

