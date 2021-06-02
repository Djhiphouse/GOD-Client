package me.bratwurst.utils;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;

public  class grieferutil {
    public static ItemStack createHologramm(String text, double x, double y, double z) {
        ItemStack item = new ItemStack((Item) Items.armor_stand);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound entityTag = new NBTTagCompound();
        NBTTagCompound itemHand = new NBTTagCompound();
        NBTTagList equipment = new NBTTagList();
        NBTTagCompound boots = new NBTTagCompound();
        NBTTagCompound leggins = new NBTTagCompound();
        NBTTagCompound chestplate = new NBTTagCompound();
        NBTTagCompound skull = new NBTTagCompound();
        NBTTagCompound black = new NBTTagCompound();
        NBTTagCompound display = new NBTTagCompound();

        black.setInteger("color", 1908001);
        display.setTag("display", black);

        entityTag.setInteger("Invulnerable" ,1);
        entityTag.setInteger("NoBasePlate", 1);
        entityTag.setInteger("ShowArms", 1);
        entityTag.setString("CustomName", EnumChatFormatting.RED + "[Owner] - ServerHacked");
        entityTag.setInteger("CustomNameVisible", 1);

        itemHand.setString("id", "minecraft:command_block");
        itemHand.setInteger("Count", 64);

        boots.setString("id", "diamond_boots");
        boots.setInteger("Count", 1);
        boots.setTag("tag", display);

        leggins.setString("id", "diamond_leggings");
        leggins.setInteger("Count", 1);
        leggins.setTag("tag", display);

        chestplate.setString("id", "diamond_chestplate");
        chestplate.setInteger("Count", 1);
        chestplate.setTag("tag", display);

        skull.setString("id", "skull");
        skull.setInteger("Count", 1);
        skull.setInteger("Damage", 3);
        NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("SkullOwner", text);
        skull.setTag("tag", skullOwner);

        equipment.appendTag(itemHand);
        equipment.appendTag(boots);
        equipment.appendTag(leggins);
        equipment.appendTag(chestplate);
        equipment.appendTag(skull);

        entityTag.setTag("Equipment", equipment);
        base.setTag("EntityTag", (NBTBase)entityTag);
        item.setTagCompound(base);
        NBTTagList pos = new NBTTagList();
        pos.appendTag(new NBTTagDouble(x));
        pos.appendTag(new NBTTagDouble(y));
        pos.appendTag(new NBTTagDouble(z));

        entityTag.setTag("Pos", pos);

        base.setTag("EntityTag", entityTag);
        item.setTagCompound(base);

        return item;
    }
}
