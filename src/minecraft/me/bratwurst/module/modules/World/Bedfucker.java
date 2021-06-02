package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Bedfucker extends Module {

    private int xPos;
    private int yPos;
    private int zPos;
    private static int radius = 5;

    public Bedfucker() {
        super("BedFucker", Category.WORLD);

    }



    @EventTarget
    public void onUpdate(EventUpdate event) {
        for (int x = -radius; x < radius; x++) {
            for (int y = radius; y > -radius; y--) {
                for (int z = -radius; z < radius; z++) {
                    this.xPos = ((int) mc.thePlayer.posX + x);
                    this.yPos = ((int) mc.thePlayer.posY + y);
                    this.zPos = ((int) mc.thePlayer.posZ + z);


                    BlockPos bp = new BlockPos(this.xPos, this.yPos, this.zPos);
                    Block b = mc.theWorld.getBlockState(bp).getBlock();

                    if (b.getBlockState().getBlock() == Block.getBlockById(26)) {
                        mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, bp, EnumFacing.NORTH));
                        mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, bp, EnumFacing.NORTH));
                    } else {
                        if (b.getBlockState().getBlock() == Block.getBlockById(26)) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, bp, EnumFacing.NORTH));
                            mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, bp, EnumFacing.NORTH));
                            toggle();
                        }
                    }

                }
            }
        }
    }

}






