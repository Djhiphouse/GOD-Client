package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Nuke extends Command {
    public Nuke() {
        super("Nuke", "Nuke", "Nuke");
    }
    @Override
    public void onCommand(String command, String[] args) {

        for (int i = 0; i < 64; i++) {
            mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage("/shot get Airstrike"));
        }
    }
}
