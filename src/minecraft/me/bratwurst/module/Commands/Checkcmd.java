package me.bratwurst.module.Commands;

import io.netty.buffer.Unpooled;
import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.EnumChatFormatting;

import java.util.Random;

public class Checkcmd extends Command {
    public Checkcmd() {
        super("Checkcmd", "Checkcmd", "Schau ob commandblöcke an sind");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (!mc.isSingleplayer()) {
            PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeString("" + (new Random()).nextInt(20));
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C17PacketCustomPayload("MC|AdvCdm", packetBuffer));
        } else {
            PlayerUtil.sendChat(EnumChatFormatting.BLUE + "Du muss auf einem Server sein");
        }
    }
}