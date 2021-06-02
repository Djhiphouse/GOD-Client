package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class TrollPotion extends Command {
    public TrollPotion() {
        super("TrollPotion", "TrollPotion", "Gibe dir eine Troll Potion mit dem Command: TrollPotion");
    }
    @Override
    public void onCommand(String command, String[] args) {
        ItemStack item = new ItemStack(Items.potionitem, 1, (short) 16451);

        NBTTagList effects = new NBTTagList();
        for(int i = 1; i <= 23; i++)
        {
            NBTTagCompound effect = new NBTTagCompound();
            effect.setInteger("Amplifier", Integer.MAX_VALUE);
            effect.setInteger("Duration", Integer.MAX_VALUE);
            effect.setInteger("Id", i);
            effects.appendTag(effect);
        }

        item.setStackDisplayName("Ventus TrollPotion");
        item.setTagInfo("CustomPotionEffects", effects);




        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(4, item));
    }
}
