package me.bratwurst.module.modules.render;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class BlockOverlay extends Module {
    public static Setting Red,  Green, Gelb, Blau,thickness,NoRotate,LegitAutoBlock;
    public BlockOverlay() {
        super("BlockOverlay", Category.RENDER);
    }
    public static int Dicke;
    public static boolean RGB = false;
    @Override
    public void setup() {

        Client.setmgr.rSetting(Red = new Setting("Rot", this, false));
        Client.setmgr.rSetting(Green = new Setting("Green", this, false));
        Client.setmgr.rSetting(Gelb = new Setting("Gelb", this, false));
        Client.setmgr.rSetting(Blau = new Setting("Blau", this, false));

    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {


    }
}
