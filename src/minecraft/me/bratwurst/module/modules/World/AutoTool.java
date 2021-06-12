package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class AutoTool extends Module {
    public AutoTool() {
        super("AutoTool", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate event) {


        if (mc.objectMouseOver == null)
            return;

        BlockPos pos = mc.objectMouseOver.getBlockPos();
        if (pos == null)
            return;
        updateTool(pos);
    }

    public void updateTool(BlockPos pos) {

        Block block = mc.theWorld.getBlockState(pos).getBlock();
        float strength = 1.0F;
        int bestItemIndex = -1;
        for (int i = 0; i < 9; i++) {
            ItemStack itemStack = (Minecraft.getMinecraft()).thePlayer.inventory.mainInventory[i];
            if (itemStack != null && itemStack.getStrVsBlock(block) > strength) {
                strength = itemStack.getStrVsBlock(block);
                bestItemIndex = i;
            }
        }
        if (bestItemIndex != -1)
            (Minecraft.getMinecraft()).thePlayer.inventory.currentItem = bestItemIndex;
    }
}
