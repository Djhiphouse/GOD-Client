package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Gethwid extends Command {
    public Gethwid() {
        super("hwid", "HWID", "hwid: Hole dir deine HWID");
    }
    @Override
    public void onCommand(String command, String[] args) {
        PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Deine HWID ist"+EnumChatFormatting.AQUA +": " + EnumChatFormatting.DARK_RED + Client.hwid   + EnumChatFormatting.YELLOW +" Deine "+ EnumChatFormatting.YELLOW +"HWID "+ EnumChatFormatting.YELLOW +"wurde "+ EnumChatFormatting.YELLOW +"in Dein " + EnumChatFormatting.YELLOW +"Clipboard kopiert");
        StringSelection selection  = new StringSelection(Client.hwid);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection,selection);
    }
}
