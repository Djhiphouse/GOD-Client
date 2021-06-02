package de.Hero.settings;

import java.util.ArrayList;

import me.bratwurst.Client;
import me.bratwurst.module.Module;


/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class SettingsManager {

    private ArrayList<Setting> settings;

    public SettingsManager(){
        this.settings = new ArrayList<>();
    }

    public void rSetting(Setting in){
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings(){
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod){
        ArrayList<Setting> out = new ArrayList<>();
        for(Setting s : getSettings()){
            if(s.getParentMod().equals(mod)){
                out.add(s);
            }
        }
        return out;
    }

    public Setting getSettingByMod(String name, Module module) {
        for (Setting setting : getSettings()) {
            if (setting.getSettingname().equalsIgnoreCase(name))
                if (setting.getModule() == module)
                    return setting;
        }
        return null;
    }

    public Setting getSettingByName(String name){
        for(Setting set : getSettings()){
            if(set.getName().equalsIgnoreCase(name)){
                return set;
            }
        }
        System.err.println("["+ Client.instance.getCLIENT_NAME() + "] Error Setting NOT found: '" + name +"'!");
        return null;
    }

    public Setting getSettingByName(Module m, String name){
        for(Setting set : getSettings()){
            if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == m){
                return set;
            }
        }
        System.err.println("["+ Client.instance.getCLIENT_NAME() + "] Error Setting NOT found: '" + name +"'!");
        return null;
    }


}