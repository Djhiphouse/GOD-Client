package me.bratwurst.event.events;

import me.bratwurst.event.Event;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public abstract class onPlayerBlockPlacement extends Event {

    abstract void onPlayerBlockPlacement(BlockPos hitPos, EnumFacing side, ItemStack currentItem, float f, float f1, float f2);
}
