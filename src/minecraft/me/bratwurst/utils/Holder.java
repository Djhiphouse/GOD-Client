package me.bratwurst.utils;

import java.util.ArrayList;
import java.util.List;

public class Holder {
    private static final Time TIME_HELPER = new Time();
    private static final List<Long> TPS_TIMES = new ArrayList<Long>();
    private static long lastPacketMS = -1L;
    private static double TPS =  -0.5;

    public static Time getTimeHelper() {
        return TIME_HELPER;
    }

    public static List<Long> getTpsTimes() {
        return TPS_TIMES;
    }

    public static long getLastPacketMS() {
        return lastPacketMS;
    }

    public static void setLastPacketMS(long lastPacketMS) {
        Holder.lastPacketMS = lastPacketMS;
    }

    public static double getTPS() {
        return TPS;
    }

    public static void setTPS(double TPS) {
        Holder.TPS = TPS;
    }
}
