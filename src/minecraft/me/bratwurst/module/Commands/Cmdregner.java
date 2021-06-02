package me.bratwurst.module.Commands;

import io.netty.buffer.Unpooled;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.EnumChatFormatting;

import java.util.Random;

public class Cmdregner extends Command {
    public Cmdregner() {
        super("Cmdregner", "Cmdregner", "Cmdregner: lasse über all cmd vom Himmel fallen");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (args.length != 0) {
            if (!PlayerUtil.isCreative()) {
                PlayerUtils.sendMessage("musst im Creative Mode sein!");
                return;
            }
            String str = "";
            for (byte b = 0; b < args.length; b = (byte)(b + 1)) {
                str = String.valueOf(String.valueOf(str)) + args[b] + " ";
                PlayerUtil.placeStackInHotbar(generateItem(str));
                PlayerUtils.sendMessage("hast das Item erfolgreich bekommen!");
            }
        } else {
            PlayerUtils.sendMessage("<Befehl>");
        }
    }

    public ItemStack generateItem(String befehl) {
        ItemStack itm = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "FallingSand");
        blockEntityTag.setInteger("SpawnCount", 1);
        blockEntityTag.setInteger("SpawnRange", 25000);
        blockEntityTag.setInteger("RequiredPlayerRange", 1000);
        blockEntityTag.setInteger("MinSpawnDelay", 20);
        blockEntityTag.setInteger("MaxSpawnDelay", 20);
        blockEntityTag.setInteger("MaxNearbyEntities", 100);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setString("Tile", "minecraft:command_block");
        spawnData.setString("Block", "minecraft:command_block");
        spawnData.setInteger("Time", 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Command", befehl);
        spawnData.setTag("TileEntityData", (NBTBase)tag);
        blockEntityTag.setTag("SpawnData", (NBTBase)spawnData);
        base.setTag("BlockEntityTag", (NBTBase)blockEntityTag);
        itm.setTagCompound(base);
        return itm;
    }
}