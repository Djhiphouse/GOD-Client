package me.bratwurst.manager.network;

import me.bratwurst.Client;
import me.bratwurst.adminNotifications.AdminNotification;
import me.bratwurst.adminNotifications.AdminNotificationManager;
import me.bratwurst.adminNotifications.AdminNotificationType;
import me.bratwurst.module.Commands.BanCommand;
import me.pseey.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import javax.swing.*;

public class IRCClient extends WebSocketListener {

    private WebSocket server;

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        server = webSocket;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

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

        } else {
            PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "GodChat: " + EnumChatFormatting.DARK_RED + "[GodUser]" + EnumChatFormatting.DARK_RED + " >> " + EnumChatFormatting.GREEN + text);

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