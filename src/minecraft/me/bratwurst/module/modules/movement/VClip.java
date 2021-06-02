package me.bratwurst.module.modules.movement;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class VClip extends Module {
    public Setting Up, Down, Normal, MoveFix;
    public static Setting mode1;

    public VClip() {
        super("VClip", Category.MOVEMENT);

    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        double x = mc.thePlayer.posX;
        double y = mc.thePlayer.posY;
        double z = mc.thePlayer.posZ;
       if (Minecraft.getMinecraft().gameSettings.keyBindSneak.pressed) {

           mc.thePlayer.setPositionAndUpdate(x, y - 3, z);
           mc.thePlayer.setPositionAndUpdate(x, y - 3, z);
           toggle();

        }
    }
}
