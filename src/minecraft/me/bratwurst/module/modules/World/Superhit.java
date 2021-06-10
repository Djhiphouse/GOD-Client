package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.pathfinding.CustomVec3;
import me.bratwurst.manager.pathfinding.PathfindingUtils;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Superhit extends Module {
    public ArrayList<CustomVec3> path = new ArrayList<>();
    public Superhit() {
        super("Superhit", Category.WORLD);
    }

    public static float yaw;
    public float pitch;
    public static int Onhit = 0;


    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Entity target : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (target instanceof  EntityPlayer && target != null) {
                if (target != mc.thePlayer) {
                    if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityPlayer) {
                        teleportAndAttack(target);

                    }

                }
            }
        }

    }

    public void teleportAndAttack(Entity target) {
        CustomVec3 from = new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        CustomVec3 to = new CustomVec3(target.posX, target.posY, target.posZ);

        path = PathfindingUtils.computePath(from, to);

        for(CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));

        // CLIENT SIDED
        mc.thePlayer.swingItem();

        // SERVER SIDED
        // mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());

        Minecraft.getMinecraft().playerController.attackEntity(Minecraft.getMinecraft().thePlayer, target);
        Collections.reverse(path);

        for(CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));
    }

    public void teleport(int x, int y, int z) {
        CustomVec3 from = new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        CustomVec3 to = new CustomVec3(x, y, z);

        path = PathfindingUtils.computePath(from, to);

        for(CustomVec3 paths : path)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(paths.getX(), paths.getY(), paths.getZ(), true));

        mc.thePlayer.setPosition(x, y, z);
    }
}