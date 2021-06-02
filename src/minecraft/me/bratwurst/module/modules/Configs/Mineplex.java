package me.bratwurst.module.modules.Configs;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.ConfigManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.SaveLoad;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

import java.io.File;

public class Mineplex extends Module {
    public Mineplex() {
        super("Mineplex", Category.Configs);
    }


    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(".config load Mineplex");
        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + " Mineplex Config Geladen");
        if (!Client.moduleManager.getModuleByName("HUD").isEnabled()) {
            Client.moduleManager.getModuleByName("HUD").toggle();

        }
        toggle();

    }
}
