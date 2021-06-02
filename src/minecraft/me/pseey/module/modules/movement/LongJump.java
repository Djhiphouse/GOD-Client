package me.pseey.module.modules.movement;

import me.pseey.event.EventTarget;
import me.pseey.event.events.EventMotionUpdate;
import me.pseey.module.Category;
import me.pseey.module.Module;
import me.pseey.module.modules.combat.AntiBot;
import me.pseey.utils.TimeHelper;
import me.pseey.utils.Vec3d;
import me.pseey.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.*;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class LongJump extends Module {
    public LongJump() {
        super("LongJump", Category.MOVEMENT);
    }

    @Override
    public void onDisable() {
        JumpCheck = true;
        mc.timer.timerSpeed = 1F;
        super.onDisable();
    }

    public boolean JumpCheck = true;

    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (!isMoving()) return;
        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(),true);
        if (mc.thePlayer.onGround) {
            mc.timer.timerSpeed = 1F;
            mc.thePlayer.motionY += 0.5F;
        }else {
            mc.timer.timerSpeed = 2F;
            mc.thePlayer.motionY -= 0.1F;

        }
    }

    public static boolean isMoving() {
        return mc.thePlayer != null && (mc.thePlayer.movementInput.moveForward != 0F || mc.thePlayer.movementInput.moveStrafe != 0F);
    }



    public static float getSpeed() {
        return (float) Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
    }
}
