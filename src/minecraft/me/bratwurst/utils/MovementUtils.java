package me.bratwurst.utils;

import me.bratwurst.event.events.EventMove;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;

public class MovementUtils {
    protected static /* synthetic */ Minecraft mc;

    static {
        mc = Minecraft.getMinecraft();
    }

    public static double getDirection() {
        float f2 = MovementUtils.mc.thePlayer.rotationYaw;
        if (MovementUtils.mc.thePlayer.moveForward < 0.0f) {
            f2 += 180.0f;
        }
        float f3 = 1.0f;
        if (MovementUtils.mc.thePlayer.moveForward < 0.0f) {
            f3 = -0.5f;
        } else if (MovementUtils.mc.thePlayer.moveForward > 0.0f) {
            f3 = 0.5f;
        }
        if (MovementUtils.mc.thePlayer.moveStrafing > 0.0f) {
            f2 -= 90.0f * f3;
        }
        if (MovementUtils.mc.thePlayer.moveStrafing < 0.0f) {
            f2 += 90.0f * f3;
        }
        return Math.toRadians(f2);
    }
    public static void setSpeed(EventMove moveEvent, double moveSpeed, float pseudoYaw, double pseudoStrafe, double pseudoForward) {
        double forward = pseudoForward;
        double strafe = pseudoStrafe;
        float yaw = pseudoYaw;
        if (pseudoForward != 0.0D) {
            if (pseudoStrafe > 0.0D) {
                yaw = pseudoYaw + (float)(pseudoForward > 0.0D ? -45 : 45);
            } else if (pseudoStrafe < 0.0D) {
                yaw = pseudoYaw + (float)(pseudoForward > 0.0D ? 45 : -45);
            }

            strafe = 0.0D;
            if (pseudoForward > 0.0D) {
                forward = 1.0D;
            } else if (pseudoForward < 0.0D) {
                forward = -1.0D;
            }
        }

        if (strafe > 0.0D) {
            strafe = 1.0D;
        } else if (strafe < 0.0D) {
            strafe = -1.0D;
        }

        double mx = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
        double mz = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
        moveEvent.x = forward * moveSpeed * mx + strafe * moveSpeed * mz;
        moveEvent.z = forward * moveSpeed * mz - strafe * moveSpeed * mx;
    }
    public static void speedTest(float f2) {
        MovementUtils.mc.thePlayer.motionX += (double)f2;
        MovementUtils.mc.thePlayer.motionZ += (double)f2;
    }

    public static double randomNumber(double d2, double d3) {
        return Math.random() * (d2 - d3) + d3;
    }

    public static void damagePlayer() {
        double d2 = MovementUtils.mc.thePlayer.posX;
        double d3 = MovementUtils.mc.thePlayer.posY;
        double d4 = MovementUtils.mc.thePlayer.posZ;
        float f2 = 3.1f;
        if (MovementUtils.mc.thePlayer.isPotionActive(Potion.jump)) {
            f2 += (float)MovementUtils.mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1.0f;
        }
        for (int i2 = 0; i2 < (int)((double)f2 / (MovementUtils.randomNumber(0.089, 0.0849) - 0.001 - Math.random() * (double)2.0E-4f - Math.random() * (double)2.0E-4f) + 18.0); ++i2) {
            mc.getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(d2, d3 + MovementUtils.randomNumber(0.0655, 0.0625) - MovementUtils.randomNumber(0.001, 0.01) - Math.random() * (double)2.0E-4f, d4, false));
            mc.getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(d2, d3 + Math.random() * (double)2.0E-4f, d4, false));
        }
        mc.getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer(true));
    }

    public static boolean isKeyMovePressed() {
        return MovementUtils.mc.gameSettings.keyBindForward.isPressed() || MovementUtils.mc.gameSettings.keyBindBack.isPressed() || MovementUtils.mc.gameSettings.keyBindRight.isPressed() || MovementUtils.mc.gameSettings.keyBindLeft.isPressed();
    }

    public static boolean isOnGround(double d2) {
        return !MovementUtils.mc.theWorld.getCollidingBoundingBoxes(MovementUtils.mc.thePlayer, MovementUtils.mc.thePlayer.getEntityBoundingBox().offset(0.0, -d2, 0.0)).isEmpty();
    }

    public static float getSpeed() {
        return (float)Math.sqrt(MovementUtils.mc.thePlayer.motionX * MovementUtils.mc.thePlayer.motionX + MovementUtils.mc.thePlayer.motionZ * MovementUtils.mc.thePlayer.motionZ);
    }

    public static boolean isMoving() {
        return MovementUtils.mc.thePlayer != null && (MovementUtils.mc.thePlayer.movementInput.moveForward != 0.0f || MovementUtils.mc.thePlayer.movementInput.moveStrafe != 0.0f);
    }
}

