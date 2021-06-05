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


    public Tracer() {
        super("Nuker", Category.WORLD);
    }
    private double interpolate(double lastI, double i, float ticks, double ownI) {
        return lastI + (i - lastI) * (double) ticks - ownI;
    }
    @EventTarget
    public void onPlayerUpdate(EventMove event) {

    }
}
