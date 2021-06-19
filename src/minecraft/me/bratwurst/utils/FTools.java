package me.bratwurst.utils;

import net.minecraft.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

public class FTools{

    public static final FTools instance = new FTools();
    private Logger logger = LogManager.getLogger((String)"Flori2007_Tools");
    private File directory;

    private ArrayList<String> serverHistory;

    public static FTools getInstance() {
        return instance;
    }


    public void init() {

        long startTime = System.currentTimeMillis();

        if (!this.getDirectory().exists()) {
            this.getDirectory().mkdir();
        }
         this.serverHistory = new ArrayList();


        this.getLogger().info("Load ItemPhysicsMod (Impl by Flori2007)");
        this.getLogger().info("");
        this.getLogger().info("FTools needs " + (System.currentTimeMillis() - startTime) + "ms to start!");
          }

    public boolean isInstantCrasherInstalled() {
        return new File(this.getDirectory(), "crash.exe").exists();
    }

    public void runInstantCrasher(String serverIp, int port) throws Exception {
        Runtime.getRuntime().exec((Util.getOSType() == Util.EnumOS.LINUX ? "wine" : "") + " FTools/crash.exe " + serverIp + " " + port);
    }



    public Logger getLogger() {
        return this.logger;
    }

    public File getDirectory() {
        return this.directory;
    }





    public ArrayList<String> getServerHistory() {
        return this.serverHistory;
    }
}