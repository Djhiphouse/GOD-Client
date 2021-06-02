package me.pseey.utils.player;

import me.pseey.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PlayerUtils {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static void sendMessage(Object message, boolean prefix) {
        String msg = message.toString();
        if (prefix)
            msg = String.valueOf(Client.getInstance().getCLIENT_PREFIX()) + msg;
        mc.thePlayer.addChatComponentMessage( new ChatComponentText(msg));
    }

    public static void sendMessage(Object message) {
        String msg = message.toString();
        mc.thePlayer.addChatComponentMessage( new ChatComponentText(msg));
    }
}
