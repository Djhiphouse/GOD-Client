package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class AdminGui extends Command {
    public AdminGui() {
        super("Ag", "Adminguihack", "Adminguihackt: Hack das admingui");
    }

    @Override
    public void onCommand(String command, String[] args) {
        ItemStack item = new ItemStack(Blocks.chest);
        final NBTTagCompound base = new NBTTagCompound();
        base.setString("SkullOwner", "Bratwurst001");
        final NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("Id", "309");
        final NBTTagCompound display = new NBTTagCompound();
        display.setString("Name", EnumChatFormatting.BLUE + "Menü");
        final NBTTagCompound properties = new NBTTagCompound();
        final NBTTagList textures = new NBTTagList();
        final NBTTagCompound tag = new NBTTagCompound();
        item.setTagCompound(base);


        base.setTag("display", display);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue((Packet)new C10PacketCreativeInventoryAction(5, item));
    }
}
