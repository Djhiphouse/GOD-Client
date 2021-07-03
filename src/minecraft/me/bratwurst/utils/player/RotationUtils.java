package me.bratwurst.utils.player;

import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.utils.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import optifine.MathUtils;

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
    public static float[] getRotations(double posX, double posY, double posZ) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        double x = posX - player.posX;
        double y = posY - (player.posY + (double)player.getEyeHeight());
        double z = posZ - player.posZ;
        double dist = (double)MathHelper.sqrt_double(x * x + z * z);
        float yaw = (float)(Math.atan2(z, x) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float)(-(Math.atan2(y, dist) * 180.0D / 3.141592653589793D));
        return new float[]{yaw, pitch};
    }

    public static float[] getRotationsEntity(EntityLivingBase entity) {
        return  PlayerUtil.isMoving2() ? getRotations(entity.posX + MathUtils.randomNumber(0.03D, -0.03D), entity.posY + (double)entity.getEyeHeight() - 0.4D + MathUtils.randomNumber(0.07D, -0.07D), entity.posZ + MathUtils.randomNumber(0.03D, -0.03D)) : getRotations(entity.posX, entity.posY + (double)entity.getEyeHeight() - 0.4D, entity.posZ);
    }

    public static int randomNumber(int max, int min) {
        return Math.round(min + (float) Math.random() * ((max - min)));
    }

}
