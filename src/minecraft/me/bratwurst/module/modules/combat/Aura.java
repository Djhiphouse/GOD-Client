package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.Event;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.Event2D;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.World.Clientfriend;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.RenderGuiEvent;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.sql.Time;
import java.util.Random;

public class Aura extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, maxCps, Range, FailHits, Rotate, AutoBlock, NoRotate, LegitAutoBlock, Movefix, Smoth,Criticalshits,
            Throughwalls,AutoEz,AutoGG;
    public static Boolean noraote = false;
    public static Boolean noraote2 = false;
    public static float yaw;
    public float pitch;

    public boolean Attack = false;
    public boolean rotation = false, bocking = false;

    public Aura() {
        super("Aura", Category.COMBAT);

    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(minCps = new Setting("MinCPS", this, 8, 1, 20, false));
        Client.setmgr.rSetting(maxCps = new Setting("MaxCPS", this, 8, 1, 20, false));
        Client.setmgr.rSetting(Range = new Setting("Range", this, 3.8, 1, 8, false));
        Client.setmgr.rSetting(FailHits = new Setting("FailHits", this, false));
        Client.setmgr.rSetting(AutoBlock = new Setting("AutoBlock", this, false));
        Client.setmgr.rSetting(NoRotate = new Setting("NoRotate", this, false));
        Client.setmgr.rSetting(Movefix = new Setting("Movefix", this, false));
        Client.setmgr.rSetting(Rotate = new Setting("Rotate++", this, false));
        Client.setmgr.rSetting(LegitAutoBlock = new Setting("LegitAutoBlock", this, false));
        Client.setmgr.rSetting(Smoth = new Setting("Smothrotate", this, false));
        Client.setmgr.rSetting(Throughwalls = new Setting("Throughwalls", this, false));
        Client.setmgr.rSetting(AutoEz = new Setting("AutoEz", this, false));
        Client.setmgr.rSetting(AutoGG = new Setting("AutoGG", this, false));
        Client.setmgr.rSetting(Criticalshits = new Setting("Criticalshits", this, true));

    }

    //  public boolean MoveFix = true;
    public static int Groundticks = 0;
    public static int Airticks = 0;
public static boolean Criticalshitsallow;
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                    String tname = target.getName();
                    if (target.getDistanceToEntity(mc.thePlayer) <= Range.getValDouble()
                            && !FreundManager.getInstance().isFriend(tname) && target instanceof EntityPlayer && target.getDistanceToEntity(mc.thePlayer) <= Range.getValDouble() && target.getUniqueID() != null && !FreundManager.getInstance().isFriend(tname)) {
                        String TargetName = target.getName();
                        String UUIId = target.getUniqueID().toString();

                        //onrender(Event);
                        this.target1 = target;
                        if (target1.onGround) {
                            Groundticks++;
                        } else if (target1.isAirBorne) {
                            Airticks++;
                        }
                        if (!Throughwalls.getValBoolean() && !mc.thePlayer.canEntityBeSeen(target)) {

                            return;
                        }
                        if (Criticalshits.getValBoolean() && target.getDistanceToEntity(mc.thePlayer) <= Range.getValInt() ) {
                            Criticalshitsallow = true;
                        }else {
                            Criticalshitsallow = false;
                        }
                            //     PlayerUtils.sendMessage(EnumChatFormatting.AQUA+ "--------------------------------------------------------------------------------------------------------------------");
                            //   PlayerUtils.sendMessage("UUid: " + target1.getUniqueID().toString() + "Name: " + target1.getName() +  " Coustumname: " + target1.getCustomNameTag() + " Groundticks:  " + Groundticks + " Airticks: " + Airticks + " Ticksexited: " + target1.ticksExisted + " leben: " + target1.getHealth() + " Inventotysize: " + target1.getInventory().length + " falldistance: " + target1.fallDistance);


                            if (Rotate.getValBoolean()) {
                                float[] rotate = Aacrotate((EntityPlayer) target1);
                                yaw = rotate[0];
                                pitch = rotate[1];
                                mc.thePlayer.rotationYawHead = rotate[0];
                                mc.thePlayer.rotationYaw = yaw;
                                mc.thePlayer.rotationPitchHead = pitch;
                                mc.thePlayer.rotationPitch = pitch;
                            }
                            if (target.isDead && target.ticksExisted > 20 && AutoGG.getValBoolean()) {
                                PlayerUtil.SendPacketchat(target.getName() + " " + "GG");
                            }
                        if (target.isDead && target.ticksExisted > 20 && AutoEz.getValBoolean()) {
                            PlayerUtil.SendPacketchat(target.getName() + " " + "EZ Kill");
                        }
                        if (FailHits.getValBoolean()) {
                            Attack(target, e);
                        }
                        if (AutoBlock.getValBoolean()) {
                            autoB();
                        }
                        if (LegitAutoBlock.getValBoolean()) {
                            if (mc.thePlayer.isUsingItem() && bocking) {
                                bocking = false;
                            }
                            mc.thePlayer.setItemInUse(mc.thePlayer.getCurrentEquippedItem(), 20000);
                            bocking = true;
                        }
                    } else {
                        target1 = null;
                    }
                }
            }
        }
    }

    public void Attack(EntityPlayer entity, EventMotionUpdate e) {
        if (Clientfriend.antibot.contains(entity)) {
            return;
        }
        if (!Client.getInstance().getModuleManager().getModuleByName("Clientfriend").isToggle()) {
            Clientfriend.antibot.clear();
        }
        if (AntiBot.antibot.contains(entity)) {
            return;
        }
        if (!Client.getInstance().getModuleManager().getModuleByName("AntiBot").isToggle()) {
            AntiBot.antibot.clear();
        }


        if (NoRotate.getValBoolean()) {
            if (TimeHelper.hasPassed(randomClickDelay(minCps.getValDouble(), maxCps.getValDouble()))) {
                Attack = true;
                mc.playerController.attackEntity(mc.thePlayer, entity);
                mc.thePlayer.swingItem();

                TimeHelper.reset();
            }
            Attack = false;
        } else {
            float[] rotate = Aacrotate((EntityPlayer) target1);
            yaw = rotate[0];
            pitch = rotate[1];
            mc.thePlayer.rotationYawHead = rotate[0];
            e.setYaw(yaw);
            e.setPitch(pitch);
            if (TimeHelper.hasPassed(randomClickDelay(minCps.getValDouble(), maxCps.getValDouble()))) {
                Attack = true;
                mc.playerController.attackEntity(mc.thePlayer, entity);
                mc.thePlayer.swingItem();

                TimeHelper.reset();
            }
            Attack = false;
        }

        //FailHits


        if (TimeHelper.hasPassed(randomClickDelay(minCps.getValDouble(), maxCps.getValDouble()))) {
            Attack = true;
            mc.playerController.attackEntity(mc.thePlayer, entity);
            mc.thePlayer.swingItem();

            TimeHelper.reset();
        }
        Attack = false;
    }

    @EventTarget
    public void moveEvent(EventMove event) {
        if (Movefix.getValBoolean() && target1 != null && target1.getDistanceToEntity(mc.thePlayer) >= 1.5F) {
            float f = event.getStrafe() * event.getStrafe() + event.getForward() * event.getForward();

            if (f >= 1.0E-4F) {
                f = MathHelper.sqrt_float(f);

                if (f < 1.0F) {
                    f = 1.0F;
                }

                float customStrafe = 0;
                float customForward = 0;

                f = event.getFriction() / f;
                customStrafe = event.getStrafe() * f;
                customForward = event.getForward() * f;
                float f1 = MathHelper.sin(yaw * (float) Math.PI / 180.0F);
                float f2 = MathHelper.cos(yaw * (float) Math.PI / 180.0F);
                mc.thePlayer.motionX += (double) (customStrafe * f2 - customForward * f1);
                mc.thePlayer.motionZ += (double) (customForward * f2 + customStrafe * f1);
                event.setCancelled(true);
            }
        }
    }

    public static long randomClickDelay(double minCPS, double maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }

    public void autoB() {
        if (mc.thePlayer.isUsingItem() && bocking) {
            bocking = false;
        } else {
            mc.thePlayer.setItemInUse(mc.thePlayer.getCurrentEquippedItem(), 7000);
            bocking = true;
        }
    }


    public static int CounterScale = 40;
/*
    @EventTarget
    public void onrender(Event2D e) {
        if (mc.thePlayer.getDistanceToEntity(target1) <= Range.getValInt()) {
            float width = (float) ((0 / 2) + 100);
            float height = (float) (0 / 2);

             if (target1.getHealth() == 19) {
                 CounterScale = 25;
             }else if (target1.getHealth() == 18) {
                 CounterScale = 20;
             }else if (target1.getHealth() == 17) {
                 CounterScale = 15;
             }else if (target1.getHealth() == 16) {
                 CounterScale = 10;
             }else if (target1.getHealth() == 15) {
                 CounterScale = 5;
             }else if (target1.getHealth() == 14) {
                 CounterScale = 0;
             }else if (target1.getHealth() == 13) {
                 CounterScale = -5;
             }else if (target1.getHealth() == 12) {
                 CounterScale = -10;
             }else if (target1.getHealth() == 11) {
                 CounterScale = -15;
             }else if (target1.getHealth() == 10) {
                 CounterScale = -20;
             }else if (target1.getHealth() == 9) {
                 CounterScale = -25;
             }else if (target1.getHealth() == 8) {
                 CounterScale = -25;
             }else if (target1.getHealth() == 7) {
                 CounterScale = -30;
             }else if (target1.getHealth() == 6) {
                 CounterScale = -35;
             }else if (target1.getHealth() == 5) {
                 CounterScale = -36;
             }else if (target1.getHealth() == 4) {
                 CounterScale = -37;
             }else if (target1.getHealth() == 3) {
                 CounterScale = -38;
             }else if (target1.getHealth() == 2) {
                 CounterScale = -39;
             }else if (target1.getHealth() == 1) {
                 CounterScale = -40;
             }else if (target1.isDead ) {
                CounterScale = 40;
             }
           GlStateManager.pushMatrix();
            GlStateManager.translate(250, 280, 1);
            Gui.drawRect(width + 90, height + 30, width - 80, height - 30, new Color(0, 0, 0, 190).getRGB());
            Gui.drawRect(width + CounterScale, height + -2, width - 40, height - 9, new Color(10, 66, 175, 174).getRGB());
            if(CounterScale == 0) {
                Gui.drawRect(width - CounterScale, height + -2, width - 40, height - 9, new Color(10, 66, 175, 174).getRGB());
            }
            mc.fontRendererObj.drawString(
                    EnumChatFormatting.AQUA + "Range: " + (int) target1.getDistanceToEntity(mc.thePlayer), width - 75,
                    height - 25, -1);
            mc.fontRendererObj.drawString(EnumChatFormatting.AQUA + "Health: ", width - 75,
                    height - 10, -1);
            mc.fontRendererObj.drawString(EnumChatFormatting.AQUA + "Name: " + target1.getName(), width - 75,
                    height + 5, -1);


                if (target1.getHealth() < mc.thePlayer.getHealth()) {
                    mc.fontRendererObj.drawString(
                            EnumChatFormatting.AQUA + " " + EnumChatFormatting.GREEN + "WINNING", width - 45,
                            height + 14, -1);
                } else {
                    mc.fontRendererObj.drawString(
                            EnumChatFormatting.AQUA + " " + EnumChatFormatting.RED + "LOSING", width - 75,
                            height + 14, -1);
                }


                GlStateManager.color(1f, 1f, 1f);
                GuiInventory.drawEntityOnScreen(176, 28, 25, 0, 0, target1);



                GlStateManager.popMatrix();

                GL11.glPushMatrix();

                GL11.glColor4f(1, 1, 1, 1);
                GlStateManager.scale(1.0f, 1.0f, 1.0f);
                GlStateManager.translate(580, 280, 1);
                GL11.glPopMatrix();
            }


        }

*/

/*
  @EventTarget
    public  void onMoveFix(EventMove eventMove) {
        float strafe = eventMove.getStrafe();
        float forward = eventMove.getForward();
        float friction = eventMove.getFriction();
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
    public static int hit = 0;
    public static float changerotet = 1;

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


        if (FailHits.getValBoolean()) {


            return new float[]{updateRotation(mc.thePlayer.rotationYaw, yaw+ randomrotate(-3000, 90), 180f),
                    updateRotation(mc.thePlayer.rotationPitch, pitch + randomrotate(-300, 9000), 180f)};
        } else {
            return new float[]{updateRotation(mc.thePlayer.rotationYaw, yaw + 1 / 2, 180f),
                    updateRotation(mc.thePlayer.rotationPitch, pitch + 1, 180f)};
        }

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

    @EventTarget
    public void Test() {
        if (target1.onGround) {
            Groundticks++;
        } else if (target1.isAirBorne) {
            Airticks++;
        }

        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "--------------------------------------------------------------------------------------------------------------------");
        PlayerUtils.sendMessage("UUid: " + target1.getUniqueID().toString() + "Name: " + target1.getName() + " Coustumname: " + target1.getCustomNameTag() + " Groundticks:  " + Groundticks + " Airticks: " + Airticks + " Ticksexited: " + target1.ticksExisted + " leben: " + target1.getHealth() + " Inventotysize: " + target1.getInventory().length + " falldistance: " + target1.fallDistance);

    }


    @Override
    public void onDisable() {
        super.onDisable();
        hit = 0;
        Groundticks = 0;
        Airticks = 0;
        Criticalshitsallow = false;

    }
}
