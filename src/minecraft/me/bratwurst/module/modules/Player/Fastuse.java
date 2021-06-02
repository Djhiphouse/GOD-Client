package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class Fastuse extends Module {
    public static Setting mode1;
    public static Setting minCps, maxCps, Range, FailHits, RayCast,AutoBlock,NoRotate,LegitAutoBlock,Movefix;
    public Fastuse() {
        super("Fastuse", Category.PLAYER);
        ArrayList<String> options = new ArrayList<>();
        options.add("AAC");
        options.add("Cubecraft");


        Client.setmgr.rSetting(mode1 = new Setting("Fastuse Mode", this, "AAC", options));
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("AAC")) {
            AAC();
        }else if (mode1.getValString().equalsIgnoreCase("Cubecraft")) {
            cubecraft();
        }
    }
    public void AAC() {
        final Minecraft mc = Fastuse.mc;
        if (mc.thePlayer.isEating()) {
            final Minecraft mc2 = Fastuse.mc;
            if (mc.thePlayer.onGround) {
                final Minecraft mc3 = Fastuse.mc;
                if (!mc.thePlayer.isOnLadder()) {
                    final Minecraft mc4 = Fastuse.mc;
                    if (!mc.thePlayer.isInWater()) {
                        final Minecraft mc5 = Fastuse.mc;
                        if (!mc.thePlayer.isInLava()) {
                            Fastuse.mc.timer.timerSpeed = 1.2f;
                            return;
                        }
                    }
                }
            }
        }
    }
    public void cubecraft() {
        final Minecraft mc = Fastuse.mc;
        if (mc.thePlayer.isEating()) {
            final Minecraft mc2 = Fastuse.mc;
            if (mc.thePlayer.onGround) {
                final Minecraft mc3 = Fastuse.mc;
                if (!mc.thePlayer.isOnLadder()) {
                    final Minecraft mc4 = Fastuse.mc;
                    if (!mc.thePlayer.isInWater()) {
                        final Minecraft mc5 = Fastuse.mc;
                        if (!mc.thePlayer.isInLava()) {
                            Fastuse.mc.timer.timerSpeed = 2.0f;
                            return;
                        }
                    }
                }
            }
        }
        Fastuse.mc.timer.timerSpeed = 1.0f;
    }
}