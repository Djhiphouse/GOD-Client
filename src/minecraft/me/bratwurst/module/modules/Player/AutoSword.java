package me.bratwurst.module.modules.Player;

import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.module.modules.combat.Aura;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class AutoSword extends Module {
    public AutoSword() {
        super("AutoSword", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        for (Entity e : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (e instanceof EntityPlayer && e != null) {
                if (e != mc.thePlayer && !FreundManager.getInstance().isFriend(e.getName()) && Client.getInstance().getModuleManager().getModuleByName("Aura").isEnabled()) {
                    if (e.getDistanceToEntity(mc.thePlayer) <= 5f) {
                        float damageModifier = 1;
                        int newItem = -1;
                        for(int slot = 0; slot < 9; slot++) {
                            ItemStack stack = Minecraft.getMinecraft().thePlayer.inventory.mainInventory[slot];
                            if(stack == null) {
                                continue;
                            }
                            if(stack.getItem() instanceof ItemSword) {
                                ItemSword is = (ItemSword)stack.getItem();
                                float damage = is.getDamageVsEntity();
                                if(damage > damageModifier) {
                                    newItem = slot;
                                    damageModifier = damage;
                                }
                            }
                            if(newItem > -1) {
                                Minecraft.getMinecraft().thePlayer.inventory.currentItem = newItem;
                            }
                        }
                    }
                }
            }
        }

    }
}
