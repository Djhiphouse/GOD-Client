package me.bratwurst.module.modules.Crasher;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventState;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

public class nuker extends Module {
    public Setting Bypass, Range, mccSpeed, Speeddown, InfiniteFly;
    public static Setting mode1;
    public int Rangeradius;
    public nuker() {
        super("ClickNuker", Category.EXPLOIT);
    }
    @Override
    public void setup() {

        Client.setmgr.rSetting(Range = new Setting("Range", this, 1, 1, 10, false));

    }
    @EventTarget

    public void onUpdate(EventUpdate event) {
        this.setDisplayname(EnumChatFormatting.RED + " - Radius: " + Rangeradius);
        if (Minecraft.getMinecraft().theWorld != null && event.state == EventState.POST &&  mc.gameSettings.keyBindAttack.isKeyDown()) {
            int range = Range.getValInt();
            byte radius = (byte) range;
            range = Rangeradius;

            for(int y = 3; y >= -radius; --y) {
                for(int x = -radius; x <= radius; ++x) {
                    for(int z = -radius; z <= radius; ++z) {
                        BlockPos pos = new BlockPos(Minecraft.getMinecraft().thePlayer.posX - 0.1D + (double)x, Minecraft.getMinecraft().thePlayer.posY - 0.1D + (double)y, Minecraft.getMinecraft().thePlayer.posZ - 0.5D + (double)z);
                        Block block = Minecraft.getMinecraft().theWorld.getBlockState(pos).getBlock();
                        if (this.getFacingDirection(pos) != null && !(block instanceof BlockAir)) {
                            this.eraseBlock(pos, this.getFacingDirection(pos));
                        }
                    }
                }
            }

        }
    }

    public void eraseBlock(BlockPos pos, EnumFacing facing) {
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, pos, facing));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, facing));
    }

    private EnumFacing getFacingDirection(BlockPos pos) {
        EnumFacing direction = null;
        if (!Minecraft.getMinecraft().theWorld.getBlockState(pos.add(0, 1, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.UP;
        } else if (!Minecraft.getMinecraft().theWorld.getBlockState(pos.add(0, -1, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.DOWN;
        } else if (!Minecraft.getMinecraft().theWorld.getBlockState(pos.add(1, 0, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.EAST;
        } else if (!Minecraft.getMinecraft().theWorld.getBlockState(pos.add(-1, 0, 0)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.WEST;
        } else if (!Minecraft.getMinecraft().theWorld.getBlockState(pos.add(0, 0, 1)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.SOUTH;
        } else if (!Minecraft.getMinecraft().theWorld.getBlockState(pos.add(0, 0, 1)).getBlock().isSolidFullCube()) {
            direction = EnumFacing.NORTH;
        }

        return direction;
    }
}

