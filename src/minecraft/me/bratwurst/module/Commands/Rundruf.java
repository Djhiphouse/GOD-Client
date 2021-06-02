package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

public class Rundruf extends Command {
    public Rundruf() {
        super("ad", "rundruf", "Mache einen Rundruf");
    }
    @Override
    public void onCommand(String command, String[] args) {

            if (Client.hwid.equals("L7cRBfTlht6fcBBJlo6P//H5c98L/zVFquDB5TAmEkE=")) {
                String text = String.join(" ", args);
                Client.networkClient.getIrcClient().send("rundruf:" + text);
            } else {
                PlayerUtils.sendMessage(EnumChatFormatting.RED + "Du hast dazu keine rechte!");
                return;
            }
        }
}
