package me.bratwurst.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

import javax.sound.midi.MidiFileFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public enum TPSUtils {
    instance("instance", 0);
    public static long lagms;
    private static int packetsPerSecondTemp;
    private static int packetsPerSecond;
    private static long lastMS;
    private static boolean doneOneTime;
    private static long startTime;
    private static long lastReceiveTime;
    public static double tps;
    public static double lastTps;
    private static List<Float> tpsList;
    private static float listTime;
    private static int tempTicks;

    public static float fiveMinuteTPS;
    private static TimeHelper th;
    private static DecimalFormat df;

    private TPSUtils(String s2, int n22) {
    }

    public static void onPacketReceive(Packet event) {
        lastTps = tps;
        if (event instanceof S01PacketJoinGame) {
            tps = 20.0;
            fiveMinuteTPS = 20.0f;
        }
        if (event instanceof S03PacketTimeUpdate) {
            long currentReceiveTime = System.currentTimeMillis();
            if (lastReceiveTime != -1) {
                long timeBetween = currentReceiveTime - lastReceiveTime;
                double neededTps = (double)timeBetween / 50.0;
                double niceTps = 20.0;
                double multi = neededTps / 20.0;
                tps = 20.0 / multi;
                if (tps < 0.0) {
                    tps = 0.0;
                }
                if (tps > 20.0) {
                    tps = 20.0;
                }
            }
            lastReceiveTime = currentReceiveTime;
        }
        if (event instanceof S03PacketTimeUpdate || event instanceof S00PacketKeepAlive) {
            ++packetsPerSecondTemp;
        }
    }

    public static void onUpdate() {
        if (TimeHelper.hasReached(2000)) {
            TimeHelper.reset();
            tps /= 2.0;
        }


        float tteemmpp = 0.0f;
        if (tempTicks >= 20) {
            tpsList.add(Float.valueOf((float)tps));
            tempTicks = 0;
        }
        if ((float)tpsList.size() >= listTime) {

            tpsList.add(Float.valueOf((float)tps));
        }
        for (int i2 = 0; i2 < tpsList.size(); ++i2) {
            tteemmpp += tpsList.get(i2).floatValue();
        }
        fiveMinuteTPS = tteemmpp / (float)tpsList.size();
        ++tempTicks;
        if (System.currentTimeMillis() - lastMS >= 1000) {
            lastMS = System.currentTimeMillis();
            packetsPerSecond = packetsPerSecondTemp;
            packetsPerSecondTemp = 0;
        }
        if (packetsPerSecond < 1) {
            if (!doneOneTime) {
                startTime = System.currentTimeMillis();
                doneOneTime = true;
            }
        } else {
            if (doneOneTime) {
                doneOneTime = false;
            }

        }
    }



    public static char getTPSColorCode(double tps2) {
        if (tps2 >= 17.0) {
            return 'a';
        }
        if (tps2 >= 13.0) {
            return 'e';
        }
        if (tps2 > 9.0) {
            return 'c';
        }
        return '4';
    }

    static {
        packetsPerSecondTemp = 0;
        tpsList = new ArrayList<Float>();
        listTime = 300.0f;
        tempTicks = 0;
        th = new TimeHelper();
        df = new DecimalFormat();
        fiveMinuteTPS = 0.0f;
    }
}

