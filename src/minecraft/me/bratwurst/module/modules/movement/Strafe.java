package me.bratwurst.module.modules.movement;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.Event3D;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.combat.Aura;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class Strafe extends Module {
    public Strafe() {
        super("Strafe", Category.MOVEMENT);
    }
    public boolean esp = true;
    public static double distance = 8;
    float speed = 0.0990f;

    @EventTarget
    public void onPlayerUpdate(EventMove event) {
        float direction = Strafe.mc.thePlayer.rotationYaw + (float)(Strafe.mc.thePlayer.moveForward < 0.0f ? 180 : 0) + (Strafe.mc.thePlayer.moveStrafing > 0.0f ? -90.0f * (Strafe.mc.thePlayer.moveForward < 0.0f ? -0.5f : (Strafe.mc.thePlayer.moveForward > 0.0f ? 0.5f : 1.0f)) : 0.0f) - (Strafe.mc.thePlayer.moveStrafing < 0.0f ? -90.0f * (Strafe.mc.thePlayer.moveForward < 0.0f ? -0.5f : (Strafe.mc.thePlayer.moveForward > 0.0f ? 0.5f : 1.0f)) : 0.0f);
        float xDir = (float)Math.cos((double)(direction + 90.0f) * Math.PI / 180.0);
        float zDir = (float)Math.sin((double)(direction + 90.0f) * Math.PI / 180.0);
        if (!(!Strafe.mc.gameSettings.keyBindForward.isKeyDown() && !Strafe.mc.gameSettings.keyBindLeft.isKeyDown() && !Strafe.mc.gameSettings.keyBindRight.isKeyDown() && !Strafe.mc.gameSettings.keyBindBack.isKeyDown() || Strafe.mc.thePlayer.isInWater() || Strafe.mc.thePlayer.isInLava() || Strafe.mc.thePlayer.isInWeb || Strafe.mc.thePlayer.onGround && Strafe.mc.thePlayer.isSneaking())) {
            double speed = !Strafe.mc.thePlayer.onGround && !Strafe.mc.thePlayer.isSneaking() || !Strafe.mc.thePlayer.onGround && !Strafe.mc.thePlayer.isBlocking() ? 0.24 : 0.14;
            Strafe.mc.thePlayer.motionX = (double)xDir * speed;
            Strafe.mc.thePlayer.motionZ = (double)zDir * speed;
        }

    }
}
