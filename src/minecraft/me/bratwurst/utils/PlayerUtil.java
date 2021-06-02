package me.bratwurst.utils;

import com.sun.security.ntlm.Client;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;

import java.awt.*;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;

public class PlayerUtil {
    protected static Minecraft mc = Minecraft.getMinecraft();
    private static final Potion[] blockedEffects;
    public EntityPlayer p;

    static {
        blockedEffects = new Potion[]{Potion.hunger, Potion.moveSlowdown, Potion.digSlowdown, Potion.harm, Potion.confusion, Potion.blindness, Potion.weakness, Potion.wither, Potion.poison};
    }

    public PlayerUtil() {
        this.p = mc.thePlayer;
    }

    public static void sendChat(String s) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(s);
    }

    public static void placeStackInHotbar(ItemStack itm) {
        if (mc.playerController.isInCreativeMode()) {
            mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, itm));
        }

    }


    public static void addChatMessageWithoutPrefix(String message) {
        mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

    public static boolean isCreative() {
        return mc.thePlayer.capabilities.isCreativeMode;
    }

    public static void copy(String s) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(s), (ClipboardOwner)null);
    }



    public static boolean isInLiquid() {
        return mc.thePlayer.isInsideOfMaterial(Material.lava) || mc.thePlayer.isInWater();
    }
}
