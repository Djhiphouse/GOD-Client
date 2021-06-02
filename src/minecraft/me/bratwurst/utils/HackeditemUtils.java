package me.bratwurst.utils;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;

public class HackeditemUtils {
    public static ItemStack addSpawnerToFurnance(ItemStack stack) {
        ItemStack furnance = new ItemStack(Blocks.furnace);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setShort("BurnTime", (short)0);
        blockEntityTag.setShort("CookTime", (short)0);
        blockEntityTag.setShort("CookTimeTotal", (short)200);
        blockEntityTag.setString("id", "Furnace");
        NBTTagList items = new NBTTagList();
        NBTTagCompound item = new NBTTagCompound();
        item.setByte("Count", (byte)1);
        item.setShort("Damage", (short)stack.getItemDamage());
        item.setString("id", "minecraft:mob_spawner");
        item.setShort("Slot", (short)0);
        item.setTag("tag", (NBTBase)stack.getTagCompound());
        items.appendTag((NBTBase)item);
        blockEntityTag.setTag("Items", (NBTBase)items);
        base.setTag("BlockEntityTag", (NBTBase)blockEntityTag);
        furnance.setTagCompound(base);
        return furnance;
    }
    public static ItemStack generatetpsspawner(String owner, double x, double y, double z) {
        ItemStack item = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();

        blockEntityTag.setString("EntityId", "ThrownEnderpearl");
        blockEntityTag.setInteger("SpawnCount", 2);
        blockEntityTag.setInteger("SpawnRange", 1);
        blockEntityTag.setInteger("RequiredPlayerRange", (int) Short.MAX_VALUE - 100);
        blockEntityTag.setInteger("MinSpawnDelay", 5);
        blockEntityTag.setInteger("MaxSpawnDelay", 5);
        blockEntityTag.setInteger("MaxNearbyEntities", (int) Short.MAX_VALUE - 100);

        NBTTagCompound spawnData = new NBTTagCompound();

        spawnData.setByte("shake", (byte) 0);
        spawnData.setString("ownerName", owner);

        NBTTagList pos = new NBTTagList();
        pos.appendTag(new NBTTagDouble(x));
        pos.appendTag(new NBTTagDouble(y));
        pos.appendTag(new NBTTagDouble(z));

        spawnData.setTag("Pos", pos);

        blockEntityTag.setTag("SpawnData", spawnData);
        base.setTag("BlockEntityTag", blockEntityTag);

        item.setTagCompound(base);
        return item;

    }
}
