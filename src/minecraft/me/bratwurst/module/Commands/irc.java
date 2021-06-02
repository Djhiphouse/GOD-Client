package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

import javax.swing.*;

public class irc extends Command {
    protected int Beleidigungsmeter = 0;
    protected String[] Blacklisted = {"Hurensohn", "Nuttensohn", "Scheiß Client", "Scheiße", "Nutte", "scheiße", "hure", "hs", "huso", "HS", "ez", "EZ", "Hurensöhne", "hurensohn", "Der Dev ist skid", "Skid client", "der Client ist skid"};

    public irc() {
        super("Irc", "Irc", "ingame chat");
    }

    @Override
    public void onCommand(String command, String[] args) {

        if (args.length >= 1) {

            String text = String.join(" ", args);

            if (!text.contains("Hure") && !text.contains("Hurensohn") && !text.contains("Hundesohn") && !text.contains("Sperma") && !text.contains("Penis") && !text.contains("Nigger") && !text.contains("SkidClient") && !text.contains("Dev ist Skid") && !text.contains("fick") && !text.contains("ficken") && !text.contains("ficker")) {

                Client.networkClient.getIrcClient().send(text);
            } else {
                Beleidigungsmeter++;
                if (Beleidigungsmeter == 1) {

                    PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Provokation: " + EnumChatFormatting.DARK_RED + "Hoer bitte auf zu beleidigen" + EnumChatFormatting.DARK_RED + " sonst wirst du von " + EnumChatFormatting.DARK_RED + "Client ausgeschlossen " + EnumChatFormatting.DARK_RED + "Letzte Chance.");
                } else if (Beleidigungsmeter == 2) {

                    Beleidigungsmeter++;
                    if (Beleidigungsmeter == 3) {

                        JOptionPane.showMessageDialog(null, "Du Wurdest vom Client fuer 7 Tage Blockiert", "BLOCKED", JOptionPane.CANCEL_OPTION);
                        Client.networkClient.getIrcClient().send(EnumChatFormatting.RED + Minecraft.getMinecraft().session.getUsername() + EnumChatFormatting.DARK_RED +" Wurde vom Client " + EnumChatFormatting.RED + "Gebannt " + EnumChatFormatting.YELLOW + "Grund fuer die Beleidigung: " + EnumChatFormatting.RED + text);
                        Client.networkClient.register(Client.hwid);
                         mc.shutdown();


                        }
                    }
                }
            }


        }
    }
