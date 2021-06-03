package me.bratwurst.module.modules.movement;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.Event;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.Player.Nofall;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;

import javax.vecmath.Vector3d;
import java.sql.Time;
import java.util.ArrayList;

public class Glide extends Module {
    ArrayList<Packet> Packets = new ArrayList<>();
    ArrayList<Vector3d> loc = new ArrayList<>();
    private Vector3d startVector3d;
    public double x, y, z;

    public Setting Bypass, Speed, mccSpeed, Speeddown, InfiniteFly;
    public static Setting mode1;

    public Glide() {

        super("Glide", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Mccentral");
        options.add("Vanilla");
        options.add("GlideUp");
        options.add("Down");
        options.add("FakeLag");
        options.add("BetterMccentral");
        options.add("damage");
        options.add("BetterSpartan");
        options.add("Spartan");
        options.add("NoDown");
        options.add("Test");


        Client.setmgr.rSetting(mode1 = new Setting("Glide Mode", this, "Mccentral", options));
    }

    @Override
    public void setup() {

        Client.setmgr.rSetting(Speed = new Setting("Speed", this, 1, 1, 25, false));
        Client.setmgr.rSetting(mccSpeed = new Setting("Mcccspeed", this, 1, 1, 4, true));
        Client.setmgr.rSetting(Speeddown = new Setting("Downsp", this, 600, 250, 1000, true));
        Client.setmgr.rSetting(Bypass = new Setting("Bypass", this, false));
        Client.setmgr.rSetting(InfiniteFly = new Setting("InfiniteFly", this, false));
    }

    public boolean ticktrue = false;
    public boolean tick2 = true;

    @EventTarget

    public void onUpdate(EventUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Mccentral")) {

            Damage();


        } else if (mode1.getValString().equalsIgnoreCase("Vanilla")) {
            Vanilla(Speed.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("GlideUp")) {
            Glideup();
        } else if (mode1.getValString().equalsIgnoreCase("Down")) {
            Down(Speeddown.getValInt());
        } else if (mode1.getValString().equalsIgnoreCase("FakeLag")) {

        } else if (mode1.getValString().equalsIgnoreCase("BetterMccentral")) {
            BetterMccentral(1);
        } else if (mode1.getValString().equalsIgnoreCase("damage")) {
            damage();
        } else if (mode1.getValString().equalsIgnoreCase("BetterSpartan")) {
            intave();
        } else if (mode1.getValString().equalsIgnoreCase("Spartan")) {
            Hypixel();
        } else if (mode1.getValString().equalsIgnoreCase("NoDown")) {
            NoDown();

        }else if (mode1.getValString().equalsIgnoreCase("Test")) {
           Damage2();

        }
    }

    public int ms = 163;

    public void Mccentral() {
        double y = mc.thePlayer.posY;
        if (TimeHelper.hasPassed(ms)) {
            this.mc.thePlayer.jump();
            this.mc.thePlayer.motionY -= 0.3000000119209288D;
            TimeHelper.reset();
        } else if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
            this.mc.thePlayer.jump();
            this.mc.thePlayer.motionY += 0.4000000119209288D;
        } else if (Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown()) {
            this.mc.thePlayer.motionY -= 0.02000000119209288D;


        }
    }

    public void Vanilla(int Speed) {
        if (Bypass.getValBoolean()) {
            mc.thePlayer.motionY = 0.2F;
            if (TimeHelper.hasReached(500)) {
                mc.timer.timerSpeed = Speed;
                TimeHelper.reset();
            }
        } else {


            mc.timer.timerSpeed = Speed;

        }
    }

    public void Glideup() {

        mc.thePlayer.motionY = -0.041F;
        mc.timer.timerSpeed = 2F;
        int Ms = 3000;
        me.bratwurst.manager.TimeHelper.getCurrentMS(mccSpeed.getValInt());
        if (TimeHelper.hasReached(mccSpeed.getValInt())) {
            mc.thePlayer.motionY = -0.041F;
            TimeHelper.reset();
        }
    }

    public static int tick = 0;

    public void Damage() {
        if (tick == 0) {
            final double offset = 0.060100000351667404;
            final NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getNetHandler();
            final EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            final double x = player.posX;
            final double z = player.posZ;
            final double y = player.posY;
            for (int i = 0; i < 0.50089898 / 0.551000046342611 + 1.0; ++i) {
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.060100000351667404, z, false));
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 01000237487257E-4, z, false));
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.00499999888241191 + 1.0100003516674E-5, z, false));

            }
            netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer(true));
            tick++;

        } else {
            if (mc.thePlayer.hurtTime > 0) {
             BetterMccentral(3);
            }
        }
    }
    public void Damage2() {
        if (tick == 0) {
             NetHandlerPlayClient netHandlerPlayClient = Minecraft.getMinecraft().getNetHandler();
             double x = mc.thePlayer.posX;
             double z = mc.thePlayer.posZ;
             double y = mc.thePlayer.posY;
            for (int i = 0; i < 100; ++i) {
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.060100000351667404, z, false));
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 01000237487257E-1, z, false));
                netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 0.00499999888241191 + 1.0100003516674E-1, z, false));

            }
            netHandlerPlayClient.addToSendQueueSilent(new C03PacketPlayer(true));
            tick++;

        } else {
            if (mc.thePlayer.hurtTime > 0.4 && mc.thePlayer.moveForward != 0) {
             BetterMccentral(12);
                DamageSource.hungerDamage = 0F;


            }
        }
    }
    public void Down(int delay) {
        if (TimeHelper.hasReached(delay)) {
            this.mc.thePlayer.jump();
            this.mc.thePlayer.motionY -= 0.3000000119209288D;
            TimeHelper.reset();
        }
    }


    public static boolean Groundstand;
public void Groundcheck() {
        if (mc.thePlayer.onGround) {
            Groundstand = true;
        }else {
            Groundstand = false;
        }

}
    public void FakeLag(ProcessPacketEvent e) {
        if (mc.thePlayer != null) {
            if (mc.theWorld == null) return;
            startVector3d = new javax.vecmath.Vector3d(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            final EntityOtherPlayerMP entityOtherPlayerMP = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
            entityOtherPlayerMP.inventory = mc.thePlayer.inventory;
            entityOtherPlayerMP.inventoryContainer = mc.thePlayer.inventoryContainer;
            entityOtherPlayerMP.setPositionAndRotation(mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
            entityOtherPlayerMP.rotationYawHead = mc.thePlayer.rotationYawHead;
            entityOtherPlayerMP.setSneaking(mc.thePlayer.isSneaking());
            x = mc.thePlayer.posX;
            y = mc.thePlayer.posY;
            z = mc.thePlayer.posZ;
            Packets.clear();

            loc.add(new Vector3d(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));
            Packets.add(e.getPacket());
            e.setCancelled(true);
            mc.thePlayer.motionX *= 0.8;
            mc.thePlayer.motionZ *= 0.8;
        }
    }

    public void BetterMccentral(int Speedfly) {
        Speedfly = mccSpeed.getValInt();
        if (Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
            double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
            double pitch = Math.toRadians(mc.thePlayer.rotationPitch);
            double x = -Math.sin(yaw) * Speedfly;
            double z = Math.cos(yaw) * Speedfly;
            double y = -Math.sin(pitch) * 0.1;

            mc.thePlayer.motionX = x;
            mc.thePlayer.motionZ = z;
            mc.thePlayer.motionY = y;
            mc.thePlayer.moveForward *= 19.0F;
            mc.thePlayer.moveStrafing *= 4.0F;
            if (Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
                double pitch2 = Math.toRadians(mc.thePlayer.rotationPitch);
                double yy = -Math.sin(pitch2) * 0.9;
                mc.thePlayer.motionY = yy;
                mc.thePlayer.moveForward *= 19.0F;
                mc.thePlayer.moveStrafing *= 4.0F;

            }

        }
        if (InfiniteFly.getValBoolean()) {
            if (TimeHelper.hasReached(2500)) {
                this.mc.thePlayer.jump();
                this.mc.thePlayer.motionY -= 0.3000000119209288D;
                TimeHelper.reset();
            }
        }

    }

    public static int gamemode = 0;
    public static int tickfor = 0;
    public int jumptick = 0;

    public void damage() {
        if (!PlayerUtil.isCreative()) {


            double x = mc.thePlayer.posX;
            double y = mc.thePlayer.posY;
            double z = mc.thePlayer.posZ;

            if (tickfor <= 1) {


                NetHandlerPlayClient netHandler = mc.getNetHandler();
                double offset = 0.060100000351667404D;

                for (int i = 0; i < 20; ++i) {
                    for (int j = 0; (double) j < (double) PlayerUtils.getMaxFallDist() / 0.060100000351667404D + 1.0D; ++j) {
                        netHandler.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.060100000351667404D, mc.thePlayer.posZ, false));
                        netHandler.addToSendQueueSilent(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 5.000000237487257E-4D, mc.thePlayer.posZ, false));
                    }
                }

                netHandler.addToSendQueueSilent(new C03PacketPlayer(true));
            }

            if (mc.thePlayer.onGround && mc.thePlayer.hurtTime < 20) {
                tickfor++;
            } else if (!mc.thePlayer.onGround) {
                int ticks5 = 30;
                ticks5++;
                if (TimeHelper.hasReached(1000)) {
                    PlayerUtils.sendMessage("loading");

                    TimeHelper.reset();
                }

            }

            TimeHelper.reset();


        } else {
            float Speedfly = 2F;
            double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
            double pitch = Math.toRadians(mc.thePlayer.rotationPitch);
            double xxx = -Math.sin(yaw) * Speedfly;
            double zzz = Math.cos(yaw) * Speedfly;
            double yyy = -Math.sin(pitch) * 0.1;

            mc.thePlayer.motionX = xxx;
            mc.thePlayer.motionZ = zzz;
            mc.thePlayer.motionY = yyy;
            mc.thePlayer.moveForward *= 19.0F;
            mc.thePlayer.moveStrafing *= 4.0F;
        }

    }


    public void intave() {
        if (mc.thePlayer.ticksExisted % 1 == 0) {
            mc.thePlayer.onGround = true;
            mc.thePlayer.capabilities.isFlying = true;
            double yaw = mc.thePlayer.posY;
            yaw = Math.toRadians(yaw);
            double dY = Math.sin(yaw) * 0.28D;
            mc.thePlayer.motionY = dY;
            if (!mc.thePlayer.isInWater()) {
                mc.thePlayer.ticksExisted = 15;
                mc.thePlayer.isInWater();
                mc.thePlayer.motionY = 0.0D;
            }
        }

    }

    private void Hypixel() {
        mc.thePlayer.motionY = 0.0D;
        if (mc.thePlayer.ticksExisted % 3 == 0) {
            double y = mc.thePlayer.posY - 1.0E-10D;
            mc.thePlayer.sendQueue.addToSendQueue((Packet) new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
        }
        double y1 = mc.thePlayer.posY + 1.0E-10D;
        mc.thePlayer.setPosition(mc.thePlayer.posX, y1, mc.thePlayer.posZ);
    }

    public void NoDown() {
        final Minecraft mc = Glide.mc;
        mc.thePlayer.motionY = 0.0;
        final Minecraft mc2 = Glide.mc;
        if (mc.thePlayer.ticksExisted % 5 == 0) {
            final Minecraft mc3 = Glide.mc;
            final double y = mc.thePlayer.posY - 1.0E-10;
            final Minecraft mc4 = Glide.mc;
            final NetHandlerPlayClient sendQueue = mc.thePlayer.sendQueue;
            final Minecraft mc5 = Glide.mc;
            final double posX = mc.thePlayer.posX;
            final Minecraft mc6 = Glide.mc;
            final double posY = mc.thePlayer.posY;
            final Minecraft mc7 = Glide.mc;
            sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX, posY, mc.thePlayer.posZ, true));
        }
        final Minecraft mc8 = Glide.mc;
        final double y2 = mc.thePlayer.posY + 1.0E-10;
        final Minecraft mc9 = Glide.mc;
        final EntityPlayerSP thePlayer = mc.thePlayer;
        final Minecraft mc10 = Glide.mc;
        final double posX2 = mc.thePlayer.posX;
        final double y3 = y2;
        final Minecraft mc11 = Glide.mc;
        thePlayer.setPosition(posX2, y3, mc.thePlayer.posZ);

    }

    @Override
    public void onDisable() {
        super.onDisable();
        tickfor = 0;
        tick = 0;
        jumptick = 0;
        gamemode = 0;
        mc.thePlayer.capabilities.allowFlying = false;

        mc.thePlayer.capabilities.isFlying = false;

        mc.timer.timerSpeed =  1F;
        System.out.println(mc.timer.timerSpeed);
        DamageSource.hungerDamage = 0.3F;
    }
}