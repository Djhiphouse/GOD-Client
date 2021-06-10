package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.pathfinding.CustomVec3;
import me.bratwurst.manager.pathfinding.PathfindingUtils;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;

public class Teleport extends Module {

    public ArrayList<CustomVec3> path = new ArrayList<>();

    public Teleport() {
        super("Teleport", Category.WORLD);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if(mc.gameSettings.keyBindAttack.pressed) {
            BlockPos pos = mc.objectMouseOver.getBlockPos();
            if(pos != null)
                teleport(pos.getX(), pos.getY(), pos.getZ());
            mc.gameSettings.keyBindAttack.pressed = false;
        }
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
