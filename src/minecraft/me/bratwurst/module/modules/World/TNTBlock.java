package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class TNTBlock extends Module {
    public TNTBlock() {
        super("AntiTNTBlock", Category.WORLD);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        for (final Entity e : TNTBlock.mc.theWorld.loadedEntityList) {
            if (e instanceof EntityTNTPrimed) {
                final Minecraft mc = TNTBlock.mc;
                if (mc.thePlayer.getDistanceToEntity(e) > 5.0f) {
                    continue;
                }
                final Minecraft mc2 = TNTBlock.mc;
                if (mc.thePlayer.getHeldItem() == null) {
                    continue;
                }
                final Minecraft mc3 = TNTBlock.mc;
                if (mc.thePlayer.getHeldItem().getItem() == null) {
                    continue;
                }
                final Minecraft mc4 = TNTBlock.mc;
                if (!(mc.thePlayer.getHeldItem().getItem() instanceof ItemSword)) {
                    continue;
                }
                final Minecraft mc5 = TNTBlock.mc;
                final EntityPlayerSP thePlayer = mc.thePlayer;
                final Minecraft mc6 = TNTBlock.mc;
                final ItemStack heldItem = mc.thePlayer.getHeldItem();
                final Minecraft mc7 = TNTBlock.mc;
                final Item item = mc.thePlayer.getHeldItem().getItem();
                final Minecraft mc8 = TNTBlock.mc;
                thePlayer.setItemInUse(heldItem, item.getMaxItemUseDuration(mc.thePlayer.getHeldItem()));
            }
        }
    }
}