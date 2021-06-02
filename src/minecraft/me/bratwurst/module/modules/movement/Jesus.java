package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class Jesus extends Module {

    public Jesus() {
        super("Jesus", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (isOnLiquid1()) {
            mc.thePlayer.motionY = 0.1F;
        }
    }

    public static boolean isOnLiquid1() {
        boolean onLiquid = false;
        AxisAlignedBB playerBB = mc.thePlayer.getEntityBoundingBox();
        WorldClient world = mc.theWorld;
        int y = (int) playerBB.offset(0.0D, -0.01D, 0.0D).minY;

        for (int x = MathHelper.floor_double(playerBB.minX); x < MathHelper.floor_double(playerBB.maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double(playerBB.minZ); z < MathHelper.floor_double(playerBB.maxZ) + 1; ++z) {
                Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (block != null && !(block instanceof BlockAir)) {
                    if (!(block instanceof BlockLiquid)) {
                        return false;
                    }

                    onLiquid = true;
                }
            }
        }

        return onLiquid;
    }
}
