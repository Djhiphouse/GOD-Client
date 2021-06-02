package me.bratwurst.module.Commands;

import me.bratwurst.event.EventTarget;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;

public class SpielerWirbler extends Command {
    public SpielerWirbler() {
        super("ws", "SpielerWirbler", "Fuck die owner ab");
    }


    @Override
    public void onCommand(String command, String[] args) {
        if (args.length < 1) {
            PlayerUtils.sendMessage(EnumChatFormatting.GOLD + "#SpielerWirbler <SpielerName>");
        } else {
            PlayerUtil.placeStackInHotbar(this.addItemToFurnace(this.generateItem(args[0])));
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Du hast das Item erfolgreich erhalten!");
        }

    }

    public ItemStack generateItem(String ownerName) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        NBTTagCompound spawnData = new NBTTagCompound();
        new NBTTagCompound();
        blockEntityTag.setString("EntityId", "ThrownEnderpearl");
        blockEntityTag.setInteger("RequiredPlayerRange", 32667);
        blockEntityTag.setInteger("MaxNearbyEntities", 32667);
        blockEntityTag.setInteger("SpawnCount", 10);
        blockEntityTag.setTag("SpawnData", spawnData);
        blockEntityTag.setInteger("MaxSpawnDelay", 20);
        blockEntityTag.setInteger("SpawnRange", 10);
        blockEntityTag.setInteger("MinSpawnDelay", 20);
        spawnData.setInteger("shake", 10);
        spawnData.setString("ownerName", ownerName);
        base.setTag("BlockEntityTag", blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }

    public ItemStack addItemToFurnace(ItemStack itm) {
        ItemStack furnace = new ItemStack(Blocks.furnace);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setShort("BurnTime", (short)0);
        blockEntityTag.setShort("CookTime", (short)0);
        blockEntityTag.setShort("CookTimeTotal", (short)200);
        blockEntityTag.setString("id", "Furnace");
        blockEntityTag.setString("Lock", "");
        NBTTagList items = new NBTTagList();
        NBTTagCompound item = new NBTTagCompound();
        item.setByte("Count", (byte)1);
        item.setShort("Damage", (short)itm.getItemDamage());
        item.setString("id", "minecraft:mob_spawner");
        item.setShort("Slot", (short)0);
        item.setTag("tag", itm.getTagCompound());
        items.appendTag(item);
        blockEntityTag.setTag("Items", items);
        base.setTag("BlockEntityTag", blockEntityTag);
        furnace.setTagCompound(base);
        return furnace;
    }
}
