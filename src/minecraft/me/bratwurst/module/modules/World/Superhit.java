package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

import java.util.Random;

public class Superhit extends Module {
    public Superhit() {
        super("Superhit", Category.WORLD);
    }

    public static float yaw;
    public float pitch;
    public static int Onhit = 0;

    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        for (Entity TargetHit : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (TargetHit instanceof EntityPlayer) {
                if (TargetHit != mc.thePlayer && TargetHit != null) {
                    if (TargetHit.getDistanceToEntity(mc.thePlayer) <= 95) {

                        if (Onhit != 1) {
                            System.out.println(TargetHit.getName());
                            float[] rotate = Aacrotate((EntityPlayer) TargetHit);
                            yaw = rotate[0];
                            pitch = rotate[1];
                            mc.thePlayer.rotationYawHead = rotate[0];
                            mc.thePlayer.rotationYaw = yaw;
                            mc.thePlayer.rotationPitchHead = pitch;
                            mc.thePlayer.rotationPitch = pitch;
                        }

                        if (TargetHit.getDistanceToEntity(mc.thePlayer) <= 5 && Onhit != 1) {


                            Minecraft.getMinecraft().playerController.attackEntity(mc.thePlayer, TargetHit);
                            mc.thePlayer.swingItem();
                        } else {
                            if (TimeHelper.hasReached(200)) {
                                double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
                                double x;
                                double z;
                                int distance = (int) mc.thePlayer.getDistanceToEntity(TargetHit);
                                int step = 1;
                                System.out.println(distance);
                                z = mc.thePlayer.posZ;
                                x = mc.thePlayer.posX;
                                for (int i = 0; i < 12; i++) {
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x + -Math.sin(yaw) * i, mc.thePlayer.posY, z + Math.cos(yaw) * i, mc.thePlayer.onGround));
                                    x += -Math.sin(yaw) * step;
                                    z += Math.cos(yaw) * step;
                                    mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(yaw) * 5, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(yaw) * 5);

                                    System.out.println("TP");
                                    if (TargetHit.getDistanceToEntity(mc.thePlayer) <= 3) {
                                        mc.playerController.attackEntity(mc.thePlayer, TargetHit);
                                        mc.thePlayer.swingItem();
                                        Onhit++;
                                    }
                                }


                            }
                            TimeHelper.reset();
                        }
                        int onTarget = 0;
                        if (Onhit <= 1 && mc.thePlayer.isBlocking()) {

                            double yaww = Math.toRadians(mc.thePlayer.rotationYaw);
                            double xx;
                            double zz;

                            int stepp = -1;
                            System.out.println("back");
                            zz = mc.thePlayer.posZ;
                            xx = mc.thePlayer.posX;
                            for (int ii = 0; ii < 12; ii++) {
                                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(xx + -Math.sin(yaw) * -ii, mc.thePlayer.posY, zz + Math.cos(yaw) * ii, mc.thePlayer.onGround));
                                xx += -Math.sin(yaw) * stepp;
                                zz += Math.cos(yaw) * stepp;
                                mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(yaw) * -5, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(yaw) * -5);
                                mc.gameSettings.keyBindAttack.pressed = false;
                            }
                            Onhit = 0;
                            onTarget = 0;
                            TimeHelper.reset();
                        }

                    }
                }
            }
        }
    }


    public static float[] Aacrotate(EntityPlayer e) {

        Random rdm = new Random();

        double d0 = e.posX - mc.thePlayer.posX;
        double d1 = e.posY + 1 - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        double d2 = e.posZ - mc.thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float f = (float) (MathHelper.func_181159_b(d2, d0) * 180 / Math.PI) - 90.0F;
        float f1 = (float) (-(MathHelper.func_181159_b(d1, d3) * 180 / Math.PI));
        float yaw, pitch;
        yaw = f;
        pitch = f1;
        return new float[]{updateRotation(mc.thePlayer.rotationYaw, yaw + 1 / 2, 180f),
                updateRotation(mc.thePlayer.rotationPitch, pitch + 1, 180f)};
    }

    private static float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
        float f = MathHelper.wrapAngleTo180_float(p_75652_2_ - p_75652_1_);

        if (f > p_75652_3_) {
            f = p_75652_3_;
        }

        if (f < -p_75652_3_) {
            f = -p_75652_3_;
        }

        return p_75652_1_ + f;

    }

    @Override
    public void onDisable() {
        super.onDisable();
        Onhit = 0;
    }
}
