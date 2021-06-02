package me.bratwurst.module.modules.movement;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;

import java.util.ArrayList;

public class Step extends Module {

    public Setting Bypass, Speed, mccSpeed, Stephight, InfiniteFly;
    public static Setting mode1;

    public Step() {
        super("Step", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Legit");
        options.add("Vanilla");
        Client.instance.setmgr.rSetting(new Setting("Step Anticheat", this, "Legit", options));
    }


    @EventTarget
    public void onUpdate(EventUpdate event) {
       if (mode1.getValString().equalsIgnoreCase("Legit")) {
           legit();
       }else if (mode1.getValString().equalsIgnoreCase("Vanilla")) {
          Vanilla();
       }
    }
public void legit() {
    if (mc.thePlayer.isCollidedHorizontally) {
        mc.thePlayer.stepHeight = 0.6F;
    }
}
public void Vanilla(){
    if (mc.thePlayer.isCollidedHorizontally) {
        mc.thePlayer.stepHeight = 25;
    }
}
    @Override
    public void onDisable() {
        super.onDisable();
        mc.thePlayer.stepHeight = 0.6F;
    }
}
