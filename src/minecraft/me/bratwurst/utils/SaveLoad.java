package me.bratwurst.utils;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;

import java.io.*;
import java.util.ArrayList;

public class SaveLoad {
    public File dir;
    public File configs;
    public static File dataFile;

    public SaveLoad() {
        dir = new File(Minecraft.getMinecraft().mcDataDir, Client.getInstance().getCLIENT_NAME());
        if (!dir.exists()) {
            dir.mkdir();
        }
        configs = new File(Minecraft.getMinecraft().mcDataDir, Client.getInstance().getCLIENT_NAME() + "/configs");
        if (!configs.exists()) {
            configs.mkdir();
        }
        dataFile = new File(dir, "data.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save(File file) {
        ArrayList<String> toSave = new ArrayList<String>();
        for (Module mod : Client.getInstance().getModuleManager().getModules()) {
            toSave.add("MOD:" + mod.getName() + ":" + mod.isToggle() + ":" + mod.getKey());
        }
        for (Setting set : Client.instance.setmgr.getSettings()) {
            if (set.isCheck()) {
                toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValBoolean());
            }
            if (set.isCombo()) {
                toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
            }
            if (set.isSlider()) {
                toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
            }
        }

        try {
            PrintWriter pw = new PrintWriter(file);
            for (String str : toSave) {
                pw.println(str);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void remove(File name) {
        if (name != null) {
            if (name.exists()) {
                name.delete();
            }
        }
    }

    public static void load(File file) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : lines) {
            String[] args = s.split(":");
            if (s.toLowerCase().startsWith("mod:")) {
                Module m = Client.getInstance().getModuleManager().getModuleByName(args[1]);
                if (m != null) {
                    Boolean state = Boolean.parseBoolean(args[2]);
                    if(state)
                        m.toggle();
                    m.setKey(Integer.parseInt(args[3]));
                }
            } else if (s.toLowerCase().startsWith("set:")) {
                Module m = Client.getInstance().getModuleManager().getModuleByName(args[2]);
                if (m != null) {
                    Setting set = Client.instance.setmgr.getSettingByName(args[1]);
                    if (set != null) {
                        if (set.isCheck()) {
                            set.setValBoolean(Boolean.parseBoolean(args[3]));
                        }
                        if (set.isCombo()) {
                            set.setValString(args[3]);
                        }
                        if (set.isSlider()) {
                            set.setValDouble(Double.parseDouble(args[3]));
                        }
                    }
                }
            }
        }
    }
}
