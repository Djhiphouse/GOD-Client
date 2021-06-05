package me.bratwurst.module.modules.Crasher;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class ExtraEchant extends Module {
    public ExtraEchant() {
        super("ExtraEchant", Category.EXPLOIT);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (!mc.thePlayer.capabilities.isCreativeMode) {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Das funktioniert nur im Creativemode.");

        } else if (mc.thePlayer.inventory.getStackInSlot(0) != null) {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Bitte halte denn ersten Slot frei.");
        } else {
            ItemStack item = new ItemStack(Items.skull, 1, 3);
            item.addEnchantment(Enchantment.efficiency, 3270);
            item.addEnchantment(Enchantment.fireAspect, 3270);
            item.addEnchantment(Enchantment.unbreaking, 3270);
            item.addEnchantment(Enchantment.power, 3270);
            item.addEnchantment(Enchantment.punch, 3270);
            item.addEnchantment(Enchantment.flame, 3270);
            item.addEnchantment(Enchantment.looting, 3270);
            item.addEnchantment(Enchantment.sharpness, 3270);
            mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, item));
          toggle();
        }
    }
}
