package me.bratwurst.module.modules.movement;

import com.sun.org.apache.bcel.internal.generic.FSTORE;
import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;

public class FastLader extends Module {
    public Setting Speed, Bypass, TeleportDelay, MoveFix;
    public static Setting mode1;

    public FastLader() {
        super("FastLader", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("AAC");
        options.add("Cubecraft");


        Client.setmgr.rSetting(mode1 = new Setting("FastLader Mode", this, "AAC", options));
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("AAC")) {
            AAC();
            this.setDisplayname(EnumChatFormatting.RED + " - AAC");
        } else if (mode1.getValString().equalsIgnoreCase("Cubecraft")) {
            Cubecraft();
            this.setDisplayname(EnumChatFormatting.RED + " - Cubecraft");
        }
    }

    public void AAC() {
        final Minecraft mc = FastLader.mc;
        if (mc.thePlayer.isOnLadder()) {
            final Minecraft mc2 = FastLader.mc;
            final EntityPlayerSP thePlayer = mc.thePlayer;
            thePlayer.motionY *= 1.025;
        } else {
            final Minecraft mc3 = FastLader.mc;
            final EntityPlayerSP thePlayer2 = mc.thePlayer;
            thePlayer2.motionY *= 1.0;
        }
    }

    public void Cubecraft() {
        final Minecraft mc = FastLader.mc;
        if (mc.thePlayer.isOnLadder()) {
            final Minecraft mc2 = FastLader.mc;
            final EntityPlayerSP thePlayer = mc.thePlayer;
            thePlayer.motionY *= 1.5;
        } else {
            final Minecraft mc3 = FastLader.mc;
            final EntityPlayerSP thePlayer2 = mc.thePlayer;
            thePlayer2.motionY *= 1.0;
        }
    }
}
