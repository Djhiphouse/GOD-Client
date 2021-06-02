package me.bratwurst.utils.player;

import me.bratwurst.Client;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PlayerUtils {

    public static Minecraft mc = Minecraft.getMinecraft();

    public static void sendMessage(Object message, boolean prefix) {
        String msg = message.toString();
        if (prefix)
            msg = String.valueOf(Client.getInstance().getCLIENT_PREFIX()) + msg;
        mc.thePlayer.addChatComponentMessage((IChatComponent) new ChatComponentText(msg));
    }

    public static void sendMessage(Object message) {
        String msg = message.toString();
        mc.thePlayer.addChatComponentMessage((IChatComponent) new ChatComponentText(msg));
    }
    public static float getMaxFallDist() {
        PotionEffect potioneffect = mc.thePlayer.getActivePotionEffect(Potion.jump);
        int f = potioneffect != null ? potioneffect.getAmplifier() + 1 : 0;
        return (float)(mc.thePlayer.getMaxFallHeight() + f);
    }
}
