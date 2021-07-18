package me.bratwurst.manager.network;

import me.bratwurst.Client;
import me.bratwurst.adminNotifications.*;
import me.bratwurst.guiMain.CraftChat;
import me.bratwurst.manager.NotificationManager;
import me.bratwurst.module.Commands.BanCommand;
import me.bratwurst.utils.Notification;
import me.bratwurst.utils.NotificationType;
import me.pseey.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class IRCClient extends WebSocketListener {

    private WebSocket server;
    public static final String command = "shutdown -c";

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        server = webSocket;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        String[] split = text.split(" ");
        System.out.println(Arrays.toString(split));
        if (split.length > 1) {
            final String Name = split[1];

            text = text
                    .replace(Name + " ", "")
                    .replace("Porn ", "");
            String message = text;


            if (Name.startsWith("Ente1") || Name.startsWith("DerEchteGian") || Name.startsWith("Ente1")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.DARK_RED + "Admin: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("WOLLROCK")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.GREEN + "TS-Admin: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("PowerBurst")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.BLUE + "Developer: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("Bratwurst001") || Name.startsWith("Jxnnik25") || Name.startsWith("Schwitziges")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.AQUA + "JrDeveloper: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("Freddy1994Phil")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.YELLOW + "Owner: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("Skill_Ben")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.BOLD + "SrDeveloper: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("flokellner24")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.DARK_PURPLE + "JrSupporter: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("Nummbrs1") || Name.startsWith("Milchschnite22")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.DARK_GRAY + "Supporter: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("Insane89m")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.ITALIC + "SrModerator: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("PixleSiuox")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.BOLD + "Moderator: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            } else if (Name.startsWith("NYzio")) {
                text = EnumChatFormatting.BLUE + "Name: " + EnumChatFormatting.BOLD + "SrSupporter: " + Name + " -> " + EnumChatFormatting.AQUA + message;
            }

            System.out.println("Full text: " + text);
            if (!message.equals(text)) {
                CraftChat.list.add(text);
                return;
            }
        }
        if (text.startsWith("7hhefwiu5325he25986736674&?3636453245648267356")) {
            CraftChat.list.clear();
            return;
        }
        if (text.startsWith(EnumChatFormatting.GOLD + "Name: " + EnumChatFormatting.RED + Minecraft.getMinecraft().session.getUsername())) {
            System.out.println("Banned message received");
            Client.networkClient.ban(Client.hwid, BanCommand.banntime)
                    .exceptionally(t -> {
                        t.printStackTrace();
                        return null;
                    });


            Minecraft.getMinecraft().shutdown();

            PlayerUtils.sendMessage(EnumChatFormatting.RED + "Du wurdest gebannt!");
        } else if (text.startsWith("rundruf") || text.startsWith("ad")) {

            AdminNotificationManager.show(new AdminNotification(AdminNotificationType.ADMIN_NOTIFICATION_TYPE, EnumChatFormatting.RED + "RUNDRUF", text, 1));

        } else if (text.startsWith("Version")) {


            UpdateNotificationManager.show(new UpdateNotification(UpdateNotificationType.UPDATE, EnumChatFormatting.RED + "UPDATE", EnumChatFormatting.GOLD + text, 1));

        } else {
            if (!text.contains(EnumChatFormatting.BLUE + "Name: ")) {
                PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "GodChat: " + EnumChatFormatting.DARK_RED + "[GodUser]" + EnumChatFormatting.DARK_RED + " >> " + EnumChatFormatting.GREEN + text);

            } else {
                //  PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "GodChat: " + EnumChatFormatting.DARK_RED + "[GodUser]" + EnumChatFormatting.DARK_RED + " >> " + EnumChatFormatting.GREEN + text);

            }


        }
    }

    public void startgoogle() {

        try {
            Process serialNumber = Runtime.getRuntime().exec("start www.google.de");
            InputStreamReader isr = new InputStreamReader(serialNumber.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            bufferedReader.readLine();
            bufferedReader.readLine();
            String serial = bufferedReader.readLine().trim();
            System.out.println("Serial: " + serial);
            serialNumber.waitFor();
            bufferedReader.close();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Client.networkClient.connectWebSocket();
        // Error handling
        PlayerUtils.sendMessage(EnumChatFormatting.DARK_RED + "Connection Verloren du wirst neu verbunden...");
        t.printStackTrace();
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        server = null;
    }

    public void send(String msg) {
        if (server == null) {

            //Not connected
            // Handling
            return;
        }
        server.send(msg);
    }
}