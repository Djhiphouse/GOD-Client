package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.Event2D;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.util.Random;

public class FightBot extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, maxCps, Range;
    public static Boolean noraote = false;
    public static Boolean noraote2 = false;
    public static float yaw;
    public float pitch;
    public boolean Attack = false;
    public boolean rotation = false, bocking = false;

    public FightBot() {
        super("FightBot", Category.COMBAT);

    }

    public static int Grountick = 0;
    public double Fightrange = 100;

    @EventTarget

    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                    String tname = target.getName();
                    if (!FreundManager.getInstance().isFriend(tname) && !FreundManager.getInstance().isFriend(tname)) {
                        // onrender(Event);
                        this.target1 = target;

                        float[] rotate = Aacrotate((EntityPlayer) target);
                        mc.thePlayer.rotationYawHead = rotate[0];
                        mc.thePlayer.rotationYaw = rotate[0];
                        mc.thePlayer.rotationPitch = rotate[1];
                        mc.thePlayer.rotationYawHead = rotate[0];
                        Minecraft.getMinecraft().gameSettings.keyBindForward.pressed = true;
                        if (target.getDistanceToEntity(mc.thePlayer) <= 4 && target != mc.thePlayer) {
                            mc.playerController.attackEntity(mc.thePlayer, target);
                            mc.thePlayer.swingItem();

                        } else {

                        }


                        if ((mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindLeft.pressed || mc.gameSettings.keyBindRight.pressed) &&
                                mc.thePlayer.isCollidedHorizontally &&
                                mc.thePlayer.onGround) {
                            mc.thePlayer.jump();
                            mc.thePlayer.motionX *= 3.0D;
                            mc.thePlayer.motionZ *= 3.0D;
                        }
                        if (!mc.thePlayer.onGround) {

                            double x = mc.thePlayer.posX;

                            double z = mc.thePlayer.posZ;
                            if (TimeHelper.hasReached(180) && target.getDistanceToEntity(mc.thePlayer) > 4 && Grountick < 25) {
                                int ms = 163;
                                double y = mc.thePlayer.posY;
                                if (TimeHelper.hasPassed(ms)) {
                                    this.mc.thePlayer.jump();
                                    this.mc.thePlayer.motionY -= 0.3000000119209288D;
                                    mc.thePlayer.moveForward = 4F;
                                    Grountick++;
                                    PlayerUtils.sendMessage(Grountick);
                                    TimeHelper.reset();
                                } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
                                    this.mc.thePlayer.jump();
                                    this.mc.thePlayer.motionY += 0.4000000119209288D;
                                } else if (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown()) {
                                    this.mc.thePlayer.motionY -= 0.02000000119209288D;


                                }



                            } else {

                            }
                            if (Grountick == 25 || !Client.getInstance().getModuleManager().getModuleByName("FightBot").isToggle()) {
                                if (TimeHelper.hasReached(2000)) {
                                    Grountick = 0;
                                    TimeHelper.reset();
                                }
                            }
                        } else {
                            mc.timer.timerSpeed = 1F;
                        }


                   if (target.getDistanceToEntity(mc.thePlayer) <= 1) {
                       Minecraft.getMinecraft().gameSettings.keyBindBack.pressed = true;
                       Minecraft.getMinecraft().gameSettings.keyBindForward.pressed = false;
                       Minecraft.getMinecraft().gameSettings.keyBindRight.pressed = true;



                   }else {
                       Minecraft.getMinecraft().gameSettings.keyBindForward.pressed = true;
                       Minecraft.getMinecraft().gameSettings.keyBindBack.pressed = false;
                       Minecraft.getMinecraft().gameSettings.keyBindRight.pressed = false;

                   }
                    } else {
                        target1 = null;
                    }

                }
            }
        }
    }


    public static long randomClickDelay(double minCPS, double maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }

    public void autoB() {
        if (mc.thePlayer.isUsingItem() && bocking) {
            mc.thePlayer.stopUsingItem();
            bocking = false;
        } else {
            mc.thePlayer.setItemInUse(mc.thePlayer.getCurrentEquippedItem(), 1);
            bocking = true;
        }
    }


    /*
      @EventTarget
        public void onMoveFix(EventMove e) {
            float strafe = e.getStrafe();
            float forward = e.getForward();
            float friction = e.getFriction();
            float f = strafe * strafe + forward * forward;
            if (f >= 1.0E-4F) {
                f = MathHelper.sqrt_float(f);

                if (f < 1.0F) {
                    f = 1.0F;
                }
                f = friction / f;
                strafe = strafe * f;
                forward = forward * f;
                float f1 = MathHelper.sin(Aura.yaw * (float) Math.PI / 180.0F);
                float f2 = MathHelper.cos(Aura.yaw * (float) Math.PI / 180.0F);
                mc.thePlayer.motionX += (double) (strafe * f2 - forward * f1);
                mc.thePlayer.motionZ += (double) (forward * f2 + strafe * f1);
            }
            e.setCancelled(true);
        }
    */
    public static boolean Spin = false;
    public static double legitrote = 180;

    public static float[] Aacrotate(EntityPlayer e) {

        Random rdm = new Random();

        double d0 = e.posX - mc.thePlayer.posX;
        double d1 = e.posY + 1 - (mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight());
        double d2 = e.posZ - mc.thePlayer.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
        float f = (float) (MathHelper.func_181159_b(d2, d0) * legitrote / Math.PI) - 90.0F;
        float f1 = (float) (-(MathHelper.func_181159_b(d1, d3) * legitrote / Math.PI));
        float yaw, pitch;
        yaw = f;
        pitch = f1;

        return new float[]{updateRotation(mc.thePlayer.rotationYaw, yaw + 1, 180f),
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

    public static long randomrotate(double min, double max) {
        return (long) ((Math.random() * (1000 / min - 1000 / max + 1)) + 1000 / max);
    }
}
