package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class BugUp extends Module {
    protected int trys;
    public BugUp() {
        super("BugUp", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        final Minecraft mc = BugUp.mc;
        if (mc.thePlayer != null && BugUp.mc.theWorld != null) {
            final Minecraft mc2 = BugUp.mc;
            if (mc.thePlayer.fallDistance > 0.0f && BugUp.mc.gameSettings.keyBindSneak.pressed && !mc.thePlayer.onGround) {
                final Minecraft mc3 = BugUp.mc;
                final EntityPlayerSP thePlayer = mc.thePlayer;
                final Minecraft mc4 = BugUp.mc;
                final double posX = mc.thePlayer.posX;
                final Minecraft mc5 = BugUp.mc;
                final double y = mc.thePlayer.posY + 1.0;
                final Minecraft mc6 = BugUp.mc;
                thePlayer.setPosition(posX, y, mc.thePlayer.posZ);
                this.trys = 0;
                final Minecraft mc7 = BugUp.mc;
                mc.thePlayer.fallDistance = 0.0f;
                if (this.trys > 3) {
                    this.trys = 0;
                    BugUp.mc.gameSettings.keyBindSneak.pressed = false;
                }
                final Minecraft mc8 = BugUp.mc;
                if (mc.thePlayer.onGround && this.trys > 0 && !BugUp.mc.gameSettings.keyBindSneak.pressed) {
                    this.trys = 0;
                }
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.trys = 0;
    }
}
