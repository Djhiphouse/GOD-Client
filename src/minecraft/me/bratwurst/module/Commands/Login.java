package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class Login extends Command {
    public Login() {
        super("log", "Login", "kickadmin");
    }

    @Override
    public void onCommand(String command, String[] args) {
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/register 778899 778899"));
        Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/login 778899"));
        PlayerUtils.sendMessage("Passwort: 778899");
    }
}