package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMove;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.BlickWinkel3D;
import me.bratwurst.utils.Location3D;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

import java.util.*;


public class Tracer extends Module {
    public static int blockId = 0;

    private long lastFail;

    private boolean failure;

    private BlockPos failureBlock;

    private float server_yaw;

    private float server_pitch;

    private int trys;

    private BlockPos lastArround;

    private Block lastArroundBlock;

    private TimeHelper time;

    public Tracer() {
        super("Nuker", Category.WORLD);
    }

    @EventTarget
    public void onPlayerUpdate(EventMove event) {
        for (int y = -5; y < 5; y++) {
            for (int x = -5; x < 5; x++) {
                for (int z = -5; z < 5; z++) {
                    BlockPos blockPos = mc.thePlayer.getPosition().add(x, y, z);
                    destroyBlock(blockPos);
                }
            }
        }

            try {
                for (int y = -5; y < 5; y++) {
                    for (int x = -5; x < 5; x++) {
                        for (int z = -5; z < 5; z++) {
                            BlockPos blockPos = mc.thePlayer.getPosition().add(x, y, z);
                            Block block = mc.theWorld.getBlockState(blockPos).getBlock();
                            if (block == Block.getBlockById(355)) {
                                sendLooksToPos(blockPos);
                                if (TimeHelper.hasReached(20L)) {
                                    destroyBlock(blockPos);
                                    this.trys++;
                                    this.time.reset();
                                    Thread.sleep(250L);
                                    if (failedDestroy(blockPos, block) && !getBlocksArround(blockPos).isEmpty() && this.trys > 1.0D) {
                                        this.lastFail = System.currentTimeMillis();
                                        this.failure = true;
                                        this.failureBlock = blockPos;
                                        this.trys = 0;
                                    }
                                }
                                return;
                            }
                        }
                    }
                }
            } catch (Exception exception) {
            }
        }


    private void sendLooksToPos(BlockPos blockPos) {
        Location3D startLoc = new Location3D(mc.thePlayer.posX, mc.thePlayer.posY + 1.6D, mc.thePlayer.posZ);
        Location3D endLoc = new Location3D(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        BlickWinkel3D blickWinkel3D = new BlickWinkel3D(startLoc, endLoc);
        this.server_pitch = (float) blickWinkel3D.getPitch();
        this.server_yaw = (float) blickWinkel3D.getYaw();
    }

    private ArrayList<BlockPos> getBlocksArround(BlockPos mainPos) {
        ArrayList<BlockPos> arroundBlocks = new ArrayList<>();
        Block mainBlock = mc.theWorld.getBlockState(mainPos).getBlock();
        for (int y = 0; y < 2; y++) {
            for (int x = -5; x < 5; x++) {
                for (int z = -5; z < 5; z++) {
                    BlockPos blockPos = mainPos.add(x, y, z);
                    Block block = mc.theWorld.getBlockState(blockPos).getBlock();
                    if (block == Blocks.sandstone || block == Blocks.iron_block || block == Blocks.end_stone || block == Blocks.glass || block == Blocks.glowstone || block == Blocks.chest || block.getLocalizedName().contains("Chiseled Sandstone"))
                        arroundBlocks.add(blockPos);
                }
            }
        }
        return arroundBlocks;
    }

    private BlockPos getClosetNearby(List<BlockPos> list) {
        BlockPos currentPos = null;
        double currentRange = Double.MAX_VALUE;
        Iterator<BlockPos> var6 = list.iterator();
        while (var6.hasNext()) {
            BlockPos blockPos = var6.next();
            if (mc.thePlayer.getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ()) < currentRange) {
                currentRange = mc.thePlayer.getDistance(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                currentPos = blockPos;
            }
        }
        return currentPos;
    }

    private void destroyBlock(BlockPos blockPos) {
        mc.thePlayer.sendQueue.addToSendQueue((Packet) new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
        mc.thePlayer.sendQueue.addToSendQueue((Packet) new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
    }

    private boolean failedDestroy(BlockPos blockPos, Block block) {
        return (mc.theWorld.getBlockState(blockPos).getBlock() == block);
    }
}
