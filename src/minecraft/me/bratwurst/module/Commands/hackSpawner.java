package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class hackSpawner extends Command {
    public hackSpawner() {
        super("sh", "hackSpawner", "spawnd einen hackSpawner ");
    }

    @Override
    public void onCommand(String command, String[] args) {


        command = "";
        for (int i = 0; i < args.length; i++)
            command = String.valueOf(command) + args[i] + " ";
        (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue((Packet)new C10PacketCreativeInventoryAction(36, getItemInFurnace(generateCommandBlockWithCommand(command))));
    }

    public ItemStack generateCommandBlockWithCommand(String command) {
        ItemStack item = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();



        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "FallingSand");
        blockEntityTag.setInteger("SpawnCount", 1);
        blockEntityTag.setInteger("SpawnRange", 25000);
        blockEntityTag.setInteger("RequiredPlayerRange", 100);
        blockEntityTag.setInteger("MinSpawnDelay", 10000);
        blockEntityTag.setInteger("MaxSpawnDelay", 1000);
        blockEntityTag.setInteger("MaxNearbyEntities", 10000000);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setString("Tile", "minecraft:armor_stand");
        spawnData.setString("Block", "minecraft:armor_stand");
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
        entityTag.setString("CustomName", EnumChatFormatting.RED + "HACKED");
        entityTag.setInteger("CustomNameVisible", 1);


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
        skull.setTag("tag", skullOwner);

        equipment.appendTag(itemHand);
        equipment.appendTag(boots);
        equipment.appendTag(leggins);
        equipment.appendTag(chestplate);
        equipment.appendTag(skull);

        entityTag.setTag("Equipment", equipment);
        base.setTag("EntityTag", (NBTBase)entityTag);

        spawnData.setInteger("Time", 1);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("Command", command);
        spawnData.setTag("TileEntityData", (NBTBase)tag);
        blockEntityTag.setTag("SpawnData", (NBTBase)spawnData);
        base.setTag("BlockEntityTag", (NBTBase)blockEntityTag);
        item.setTagCompound(base);
        return item;
    }

    public ItemStack getItemInFurnace(ItemStack stack) {
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
}

