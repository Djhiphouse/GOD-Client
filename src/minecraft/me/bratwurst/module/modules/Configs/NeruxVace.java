package me.bratwurst.module.modules.Configs;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class NeruxVace extends Module {
    public NeruxVace() {
        super("NeruxVace", Category.Configs);
    }


    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(".config load NeruxVace");
        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + " NeruxVace Config Geladen");
        if (!Client.moduleManager.getModuleByName("HUD").isEnabled()) {
            Client.moduleManager.getModuleByName("HUD").toggle();

        }
        toggle();

    }
}