package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

import java.util.ArrayList;

public class NoSlowdown extends Module {
    public static Setting mode1;
    public Setting Vaniila, NCP;

    public NoSlowdown() {
        super("NoSlowdown", Category.PLAYER);
        ArrayList<String> options = new ArrayList<>();
        options.add("Vanilla");
        options.add("NCP");


        Client.setmgr.rSetting(mode1 = new Setting("NoSlowdown Mode", this, "Vanilla", options));
    }


    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mode1.getValString().equalsIgnoreCase("Vanilla")) {
            Vanillanoslow();
        } else if (mode1.getValString().equalsIgnoreCase("NCP")) {
            NCp();
        }
    }

    public void Vanillanoslow() {
        if (mc.thePlayer.onGround && mc.thePlayer.moveForward != 0 && mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem()) {

        }
        if (mc.thePlayer.onGround && mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem() && mc.thePlayer.moveForward != 0) {

        }
    }

    public void NCp() {
        if (mc.thePlayer.isBlocking() || mc.thePlayer.isUsingItem()) {
            mc.thePlayer.moveStrafing *= 7;
            mc.thePlayer.moveForward *= 7;
            mc.thePlayer.setSprinting(true);
        }
    }
}