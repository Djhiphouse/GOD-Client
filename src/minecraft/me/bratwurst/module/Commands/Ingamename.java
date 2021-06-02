package me.bratwurst.module.Commands;


import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Ingamename extends Command {
    public Ingamename() {
        super("ign", "ingamename", "ign: Kopiere dein Ingamename");
    }
    @Override
    public void onCommand(String command, String[] args) {
        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Dein Ingamename ist: " + EnumChatFormatting.DARK_RED + Minecraft.getMinecraft().session.getUsername() +EnumChatFormatting.YELLOW + " Dein IngameName wurde in Dein "+EnumChatFormatting.YELLOW +"Clipboard kopiert");
        StringSelection selection  = new StringSelection(Minecraft.getMinecraft().session.getUsername());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection,selection);
    }
}
