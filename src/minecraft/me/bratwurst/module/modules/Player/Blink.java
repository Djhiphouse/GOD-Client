package me.bratwurst.module.modules.Player;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.Vec3d;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.network.Packet;

import javax.vecmath.Vector3d;


import java.util.ArrayList;

public class Blink extends Module {
    ArrayList<Packet> Packets = new ArrayList<>();
    ArrayList<Vector3d> loc = new ArrayList<>();
    private Vector3d startVector3d;
    public double x, y, z;

    public Blink() {
        super("Blink", Category.PLAYER);
    }

    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {

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

            loc.add(new Vector3d(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ));
            Packets.add(e.getPacket());
            e.setCancelled(true);
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();
        for (Packet p : Packets) {
            mc.thePlayer.sendQueue.addToSendQueue(p);
        }
        Packets.clear();

    }
}
