package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

public class Troll extends Command {
    public Troll() {
        super("troll", "troll", "");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (Client.hwid.equals("L7cRBfTlht6fcBBJlo6P//H5c98L/zVFquDB5TAmEkE=") || Client.hwid.equals("TFeZ/30Jh+XbK+BIXHhQquz8sAwfO0UfW730h+jiPGU=")) {
            String text = String.join(" ", args);
            Client.networkClient.getIrcClient().send("Troolling:" + text);
        } else {
            PlayerUtils.sendMessage(EnumChatFormatting.RED + "Du hast dazu keine rechte!");
            return;
        }
    }
}