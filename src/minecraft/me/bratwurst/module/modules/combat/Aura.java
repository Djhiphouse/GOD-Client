package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.World.Clientfriend;
import me.bratwurst.utils.*;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;
import java.util.Random;

public class Aura extends Module {
    public static Setting Rotations;
    public static Setting Movementbo;
    public static Setting Fightbo;
    public static Setting otherbo;
    public static EntityLivingBase target1;
    public static Setting minCps, maxCps, Range,  Criticalshits,
            Throughwalls,  AutoSword;
    private ArrayList<Entity> Switchtarget = new ArrayList<>();
    public static Boolean noraote = false;
    public static Boolean noraote2 = false;
    public static float yaw;
    public float pitch;
    public static boolean flagged;
    public boolean Attack = false;
    public boolean rotation = false, bocking = false;

    public Aura() {
        super("Aura", Category.COMBAT);
        ArrayList<String> options = new ArrayList<>();
        Client.setmgr.rSetting(Rotations = new Setting(EnumChatFormatting.RED + "Rotation options", this, "FailHits", options));
        options.add("FailHits");
        options.add("NoRotate");
        options.add("Switch");
        options.add("Smothrotate");
        options.add("Rotate++");
        ArrayList<String> Movement = new ArrayList<>();
        Client.setmgr.rSetting(Movementbo = new Setting(EnumChatFormatting.RED + "Movement options", this, "NormalMove", Movement));
        Movement.add("correctMovement");
        Movement.add("MovefixNormal");

        ArrayList<String> Fight = new ArrayList<>();
        Client.setmgr.rSetting(Fightbo = new Setting(EnumChatFormatting.RED + "Fight options", this, "Normal", Fight));
        Fight.add("AutoBlock");
        Fight.add("LegitAutoBlock");
        Fight.add("Normal");
        ArrayList<String> other = new ArrayList<>();
        Client.setmgr.rSetting(otherbo = new Setting(EnumChatFormatting.RED + "other options", this, "AutoGG", other));
        other.add("AutoEz");
        other.add("AutoGG");

    }

    @Override
    public void setup() {
        Client.setmgr.rSetting(minCps = new Setting(EnumChatFormatting.AQUA + "MinCPS", this, 8, 1, 20, false));
        Client.setmgr.rSetting(maxCps = new Setting(EnumChatFormatting.AQUA + "MaxCPS", this, 8, 1, 20, false));
        Client.setmgr.rSetting(Range = new Setting(EnumChatFormatting.AQUA + "Range", this, 3.8, 1, 8, false));


        Client.setmgr.rSetting(Throughwalls = new Setting(EnumChatFormatting.AQUA + "Throughwalls", this, false));

        Client.setmgr.rSetting(Criticalshits = new Setting(EnumChatFormatting.AQUA + "Criticalshits", this, true));

        Client.setmgr.rSetting(AutoSword = new Setting(EnumChatFormatting.AQUA + "AutoSword", this, false));


    }

    //  public boolean MoveFix = true;
    public static int Groundticks = 0;
    public static int Airticks = 0;
    public static boolean Criticalshitsallow;

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

        if (Rotations.getValString().equalsIgnoreCase("FailHits")) {
            this.setDisplayname(EnumChatFormatting.AQUA + " - FailHits");
        } else {
            if (Rotations.getValString().equalsIgnoreCase("Switch")) {
                this.setDisplayname(EnumChatFormatting.AQUA + " - Switch");
            } else {
                this.setDisplayname("");
            }
        }

        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                    String tname = target.getName();
                    if (target.getDistanceToEntity(mc.thePlayer) <= Range.getValDouble()
                            && !FreundManager.getInstance().isFriend(tname) && target instanceof EntityPlayer && target.getDistanceToEntity(mc.thePlayer) <= Range.getValDouble() && target.getUniqueID() != null && !FreundManager.getInstance().isFriend(tname) && !Client.getInstance().getModuleManager().getModuleByName("Scaffold").isEnabled()) {
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
                        if (Criticalshits.getValBoolean()) {
                            if (target.getDistanceToEntity(mc.thePlayer) <= Range.getValInt()) {

                                Criticalshitsallow = true;
                            } else {
                                Criticalshitsallow = false;
                            }

                        }
                        if (AutoSword.getValBoolean()) {
                            settingAutoSword(target);
                        }
                        if (Criticalshits.getValBoolean()) {
                            if (target.getDistanceToEntity(mc.thePlayer) >= Range.getValInt()) {
                                Criticalshitsallow = false;
                            }
                        }
                        //     PlayerUtils.sendMessage(EnumChatFormatting.AQUA+ "--------------------------------------------------------------------------------------------------------------------");
                        //   PlayerUtils.sendMessage("UUid: " + target1.getUniqueID().toString() + "Name: " + target1.getName() +  " Coustumname: " + target1.getCustomNameTag() + " Groundticks:  " + Groundticks + " Airticks: " + Airticks + " Ticksexited: " + target1.ticksExisted + " leben: " + target1.getHealth() + " Inventotysize: " + target1.getInventory().length + " falldistance: " + target1.fallDistance);


                        if (Rotations.getValString().equalsIgnoreCase("Rotate++")) {
                            float[] rotate = Aacrotate((EntityPlayer) target1);
                            yaw = rotate[0];
                            pitch = rotate[1];
                            mc.thePlayer.rotationYawHead = rotate[0];
                            mc.thePlayer.rotationYaw = yaw;
                            mc.thePlayer.rotationPitchHead = pitch;
                            mc.thePlayer.rotationPitch = pitch;
                        }
                        if (target.isDead && target.ticksExisted > 20 && otherbo.getValString().equalsIgnoreCase("AutoGG")) {
                            if (TimeHelper.hasReached(50)) {
                                PlayerUtil.SendPacketchat("GG");
                                TimeHelper.reset();
                            }
                        }
                        if (target.isDead && target.ticksExisted > 20 && otherbo.getValString().equalsIgnoreCase("AutoEz")) {
                            if (TimeHelper.hasReached(50)) {
                                PlayerUtil.SendPacketchat("EZ Kill");
                                TimeHelper.reset();
                            }

                        }
                        if (Rotations.getValString().equalsIgnoreCase("Rotate++")) {
                            Attack(target, e);
                        }
                        if (Rotations.getValString().equalsIgnoreCase("FailHits")) {
                            Attack(target, e);

                        }
                        if (Rotations.getValString().equalsIgnoreCase("Switch")) {

                            Attack(target, e);
                        }
                        if (Fightbo.getValString().equalsIgnoreCase("AutoBlock")) {
                            autoB();
                        }
                        if (Fightbo.getValString().equalsIgnoreCase("LegitAutoBlock")) {
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


        if (Rotations.getValString().equalsIgnoreCase("NoRotate")) {
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
    public void CorrectMovment(EventMove event) {

        if (Movementbo.getValString().equalsIgnoreCase("correctMovement") && target1 != null && target1.getDistanceToEntity(mc.thePlayer) <= 2) {
            StrafeUtil.customSilentMoveFlying(event, yaw);
            event.setCancelled(true);
            flagged = true;
        } else {
            flagged = false;
        }
    }

    @EventTarget
    public void moveEvent(EventMove event) {
        if (Movementbo.getValString().equalsIgnoreCase("NormalMove") && target1 != null && target1.getDistanceToEntity(mc.thePlayer) >= 1.5F) {
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

    public void settingAutoSword(Entity e) {
        if (e != mc.thePlayer && !FreundManager.getInstance().isFriend(e.getName()) && Client.getInstance().getModuleManager().getModuleByName("Aura").isEnabled()) {
            if (e.getDistanceToEntity(mc.thePlayer) <= 5f) {
                float damageModifier = 1;
                int newItem = -1;
                for (int slot = 0; slot < 9; slot++) {
                    ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slot];
                    if (stack == null) {
                        continue;
                    }
                    if (stack.getItem() instanceof ItemSword) {
                        ItemSword is = (ItemSword) stack.getItem();
                        float damage = is.getDamageVsEntity();
                        if (damage > damageModifier) {
                            newItem = slot;
                            damageModifier = damage;
                        }
                    }
                    if (newItem > -1) {
                        Minecraft.getMinecraft().thePlayer.inventory.currentItem = newItem;
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


        if (Rotations.getValString().equalsIgnoreCase("FailHits")) {


            return new float[]{updateRotation(mc.thePlayer.rotationYaw, yaw + randomrotate(-3000, 90), 180f),
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
