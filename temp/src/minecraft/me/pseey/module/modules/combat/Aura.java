package me.pseey.module.modules.combat;

import de.Hero.settings.Setting;
import me.pseey.Client;
import me.pseey.event.EventTarget;
import me.pseey.event.events.EventMotionUpdate;
import me.pseey.event.events.EventMove;
import me.pseey.module.Category;
import me.pseey.module.Module;
import me.pseey.utils.TimeHelper;
import me.pseey.utils.player.RotationUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class Aura extends Module {

    public Setting minCps, maxCps, Range, MoveFix;

    public float yaw, pitch;
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
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                    if (target.getDistanceToEntity(mc.thePlayer) <= Range.getValDouble()) {
                        Attack(target, e);
                    }
                }
            }
        }
    }

    public void Attack(EntityPlayer entity, EventMotionUpdate e) {
        if (AntiBot.bots.contains(entity)) {
            return;
        }
        RotationUtils.RotationToEntityAAC(entity, e);
        if (TimeHelper.hasPassed(randomClickDelay(minCps.getValDouble(), maxCps.getValDouble()))) {
            Attack = true;
            mc.playerController.attackEntity(mc.thePlayer, entity);
            mc.thePlayer.swingItem();
            System.err.println("------");
            System.err.println("Name: " + entity.getName());
            System.err.println("UUID: " + entity.getUniqueID());
            System.err.println("onGround: " + entity.onGround);
            System.err.println("isDead: " + entity.isDead);
            System.err.println("ticksExisted: " + entity.ticksExisted);
            System.err.println("groundTicks: " + entity.groundTicks);
            System.err.println("------");
            TimeHelper.reset();
        }
        Attack = false;
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

}
