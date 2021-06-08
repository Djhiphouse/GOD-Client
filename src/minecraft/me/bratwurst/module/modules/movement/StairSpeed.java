package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.BlockPos;

public class StairSpeed extends Module {
    public StairSpeed() {
        super("StairSpeed", Category.MOVEMENT);
    }

    @EventTarget

    public void onUpdate(EventMotionUpdate e) {
        EntityPlayerSP player = mc.thePlayer;
        Block below = mc.theWorld.getBlockState(new BlockPos(player.posX, player.boundingBox.minY, player.posZ)).getBlock();
        Block downBlock = mc.theWorld.getBlockState(new BlockPos(player.posX, player.boundingBox.minY, player.posZ).down()).getBlock();
        boolean down = player.posY < player.chasingPosY || player.fallDistance > 0;


        if (!player.onGround || !PlayerUtil.isMoving2())
            return;


        if (!down && below instanceof BlockStairs)
            mc.thePlayer.jump();

        if (down && downBlock instanceof BlockStairs)
            mc.thePlayer.motionY = -1;

    }
}

