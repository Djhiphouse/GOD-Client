package me.pseey.utils.player;

import me.pseey.event.events.EventMotionUpdate;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class RotationUtils {

    public static void RotationToEntity(Entity entity, EventMotionUpdate event) {
        double d0 = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d1 = entity.posY + 1 - (Minecraft.getMinecraft().thePlayer.posY + (double) Minecraft.getMinecraft().thePlayer.getEyeHeight());
        double d2 = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float f = (float) (MathHelper.func_181159_b(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f1 = (float) (-(MathHelper.func_181159_b(d1, d3) * 180.0D / Math.PI));
        event.setYaw(f);
        event.setPitch(f1);
    }

    public static void RotationToEntityAAC(Entity entity, EventMotionUpdate event) {
        double d0 = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double d1 = entity.posY + 1 - (Minecraft.getMinecraft().thePlayer.posY + (double) Minecraft.getMinecraft().thePlayer.getEyeHeight());
        double d2 = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float f = (float) (MathHelper.func_181159_b(d2, d0) * 180.0D / Math.PI) - 90.0F;
        float f1 = (float) (-(MathHelper.func_181159_b(d1, d3) * 180.0D / Math.PI));
        event.setYaw(f + randomNumber(3, -3));
        event.setPitch(f1 + randomNumber(3, -3));
    }

    public static int randomNumber(int max, int min) {
        return Math.round(min + (float) Math.random() * ((max - min)));
    }

}
