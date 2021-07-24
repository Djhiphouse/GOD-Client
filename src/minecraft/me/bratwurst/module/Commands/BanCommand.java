package me.bratwurst.module.Commands;

import java.util.concurrent.TimeUnit;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

public class BanCommand extends Command {

    public BanCommand() {
        super("ban", "Ban", "Bannt einen Spieler");
    }

    public static long banntime = TimeUnit.DAYS.toMillis(365 * 999999);

    @Override
    public void onCommand(String command, String[] args) {

        String BANMESSAGE = EnumChatFormatting.GOLD + "Name: " + "Jxnnik25" + " " + EnumChatFormatting.GOLD + "Grund: " + EnumChatFormatting.RED + args[1] + " " + EnumChatFormatting.GOLD + "Zeit: " + EnumChatFormatting.RED + "Permanent";

        if (args.length >= 2) {
            Client.networkClient.getIrcClient().send(BANMESSAGE);

        } else {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "nutze #Ban <Spieler> <Grund> <Zeit>");
            return;
        }
    }
}
