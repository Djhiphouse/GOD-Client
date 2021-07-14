package me.bratwurst.manager;

import me.bratwurst.Client;
import me.bratwurst.module.Module;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.SaveLoad;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

import java.io.File;

public class ConfigManager {
    public static final String instnatcrashi = Minecraft.getMinecraft().mcDataDir + "\\God\\Tools";
    public static final File clientDir = new File(Minecraft.getMinecraft().mcDataDir + "\\God\\Configs");
    public static final String clientDirec = Minecraft.getMinecraft().mcDataDir + "\\God";

    public static File file;

    public static void createFile() {
        File f = new File(clientDirec);
        File instantcrashi = new File(instnatcrashi);
        if (!f.exists()) {
            f.mkdir();
        }
        if (!instantcrashi.exists()) {
            instantcrashi.mkdir();
        }
    }

    public static void addConfig(String name) {
        File configF = new File(clientDirec + "\\Configs");
        if(!configF.exists())
            configF.mkdir();
        File f = new File(clientDirec + "\\Configs\\" + name + ".cfg");
        if (!f.exists()) {
            SaveLoad.save(f);
        }
    }

    public static void removeConfig(String name) {
        File configF = new File(clientDirec + "\\Configs");
        if(!configF.exists())
            configF.mkdir();
        File f = new File(clientDirec + "\\Configs\\" + name + ".cfg");
        if (f.exists()) {
            SaveLoad.remove(f);
        }
    }

    public static void loadConfig(String name) {
        File configF = new File(clientDirec + "\\Configs");
        try
        {

            if(!configF.exists())
                configF.mkdir();
            File f = new File(clientDirec + "\\Configs\\" + name + ".cfg");
            if (f.exists()) {
                for (Module m : Client.moduleManager.getEnabledModules())
                      m.toggle();

                SaveLoad.load(f);
            }else {
                MainUtil.SendClientMesage(EnumChatFormatting.RED + "Die Config wurde leider nicht gefunden");
            }
        }catch (Exception exception){

        }


    }
}
