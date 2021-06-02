package me.bratwurst.module.modules.Player;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class Eagle extends Module {
    public Eagle() {
        super("Eagle", Category.PLAYER);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (this.mc.thePlayer != null && this.mc.theWorld != null) {
            ItemStack i = this.mc.thePlayer.getCurrentEquippedItem();
            BlockPos bP = new BlockPos(this.mc.thePlayer.posX, this.mc.thePlayer.posY - 1D, this.mc.thePlayer.posZ);
            if (i != null) {
                if (i.getItem() instanceof ItemBlock) {
                    this.mc.gameSettings.keyBindSneak.pressed = false;
                    if (this.mc.theWorld.getBlockState(bP).getBlock() == Blocks.air) {
                        this.mc.gameSettings.keyBindSneak.pressed = true;
                    }
                }
            }
        }
    }
}
