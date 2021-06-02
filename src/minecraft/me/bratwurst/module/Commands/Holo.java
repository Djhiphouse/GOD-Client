package me.bratwurst.module.Commands;


import joptsimple.internal.Strings;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;

public class Holo extends Command {
    public Holo() {
        super("Holo", "Hologramm", "Spawn ein hologramm");
    }

    @Override
    public void onCommand(String command, String[] args) {

            try {
                String text = String.join(" ", args);
                Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36,
                        createHologramm(EnumChatFormatting.DARK_RED + text, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)));
            } catch (Exception e) {
               PlayerUtils.sendMessage("Fehler Exception");
            }
        }


    public static ItemStack createHologramm(String text, double x, double y, double z) {
        ItemStack itm = new ItemStack(Items.armor_stand);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setInteger("Invisible", 1);
        entityTag.setString("CustomName", text);
        entityTag.setInteger("CustomNameVisible", 1);
        entityTag.setInteger("NoGravity", 1);

        NBTTagList pos = new NBTTagList();
        pos.appendTag(new NBTTagDouble(x));
        pos.appendTag(new NBTTagDouble(y));
        pos.appendTag(new NBTTagDouble(z));

        entityTag.setTag("Pos", pos);

        base.setTag("EntityTag", entityTag);
        itm.setTagCompound(base);
        return itm;
    }

}



