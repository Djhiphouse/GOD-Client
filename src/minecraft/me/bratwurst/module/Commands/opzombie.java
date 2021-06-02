package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class opzombie extends Command {
    public opzombie() {
        super("Opz", "opzombie", "opzombie: lasse eine fake nachricht in denn chat senden");
    }

    @Override
    public void onCommand(String command, String[] args) {
        this.mc.getNetHandler().addToSendQueue((Packet)new C10PacketCreativeInventoryAction(36, Client.createItem(Items.spawn_egg, "{HideFlags:63,display:{Name:\"Zombie\r\n"+ this.mc.thePlayer.getName() + "]\",},}", 1, 54)));
        PlayerUtils.sendMessage("Op Zombie erhalten");
    }
}