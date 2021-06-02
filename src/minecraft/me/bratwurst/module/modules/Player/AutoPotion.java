package me.bratwurst.module.modules.Player;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.combat.Aura;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AutoPotion extends Module {
    public AutoPotion() {
        super("AutoPotion", Category.PLAYER);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (Aura.target1 != null) {
            if (event.isPre()) {
                Block block = mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock();
                if (block != null && block != Blocks.air)
                    return;
                for (int i = 0; i < 9; i++) {
                    if (mc.thePlayer.inventory.getStackInSlot(i) != null) {
                        if (isThrowable(i)) {
                            if (mc.gameSettings.keyBindUseItem.pressed)
                                event.setPitch(80);
                        }
                    }
                }
            } else {
                if (mc.objectMouseOver != null) {
                    Block block = mc.theWorld.getBlockState(mc.objectMouseOver.getBlockPos()).getBlock();

                    if (block != null && block != Blocks.air)
                        return;
                    for (int i = 0; i < 9; i++) {
                        if (mc.thePlayer.inventory.getStackInSlot(i) != null) {
                            if (isThrowable(i)) {
                                if (mc.gameSettings.keyBindUseItem.pressed) {
                                    mc.gameSettings.keyBindUseItem.pressed = false;
                                    mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(i));
                                    mc.getNetHandler().addToSendQueue(
                                            new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));
                                    mc.thePlayer.sendQueue.addToSendQueue(
                                            new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isThrowable(int i) {
        ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
        if (ItemPotion.isSplash(itemStack.getItemDamage())) {
            ItemPotion itemPotion = (ItemPotion) Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i)
                    .getItem();

            for (PotionEffect potionEffect : itemPotion.getEffects(itemStack)) {
                if (potionEffect.getPotionID() == Potion.heal.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.moveSpeed.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.jump.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.damageBoost.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.resistance.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.regeneration.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.fireResistance.id)
                    return true;
                if (potionEffect.getPotionID() == Potion.absorption.id)
                    return true;
            }
        }

        return false;
    }

}
