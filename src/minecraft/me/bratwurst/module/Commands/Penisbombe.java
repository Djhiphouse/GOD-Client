package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class Penisbombe extends Command {
    public Penisbombe() {
        super("Penisbombe", "Penisbombe", "Penisbombe:  erstelle eine Penisbombe");
    }
    @Override
    public void onCommand(String command, String[] args) {
        mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getSpawnerFromFurnace(FTools_ItemUtils.getPennisDestroyer())));
    }
}