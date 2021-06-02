package me.bratwurst.module.modules.movement;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class AirJump extends Module {
    public static Setting mode1;
    public AirJump() {
        super("AirJump", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Jump");
        options.add("Motion");



        Client.setmgr.rSetting(mode1 = new Setting("AirJump Mode", this, "Mccentral", options));
    }
    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (mode1.getValString().equalsIgnoreCase("Jump")) {
            Jump();
        }else if (mode1.getValString().equalsIgnoreCase("Motion")) {
            Motion();
        }
    }
    public void Jump() {
        if (Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
            mc.thePlayer.jump();
        }
    }
    public void Motion() {
        double x = mc.thePlayer.posX;
        double y = mc.thePlayer.posY;
        double z = mc.thePlayer.posZ;
        if (TimeHelper.hasReached(1000) && Minecraft.getMinecraft().gameSettings.keyBindJump.isPressed()) {
            mc.thePlayer.setPositionAndUpdate(x,y + 1,z);
            TimeHelper.reset();
        }else if (Minecraft.getMinecraft().gameSettings.keyBindJump.pressed ) {
            mc.thePlayer.setPositionAndUpdate(x,y + 1,z);
        }

    }
}
