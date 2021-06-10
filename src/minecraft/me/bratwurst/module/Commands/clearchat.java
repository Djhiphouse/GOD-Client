package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import net.minecraft.client.Minecraft;

public class clearchat extends Command {
    public clearchat() {
        super("clear", "clearchat", "Clear denn chat");
    }
    @Override
    public void onCommand(String command, String[] args) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }
}
