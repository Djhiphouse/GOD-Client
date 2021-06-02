package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.utils.HackeditemUtils;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class Tpspawner extends Command {
    public String Msg = "Du Hast Ein TpSpawner erhalten";

    public Tpspawner() {
        super("Tpspawn", "Tpspawn", "Tp wenn anders");
    }

    public String Verboten = "Du Darfst denn Dev Nicht Teleportieren" + "Du Bot!!!!!!";


    @Override
    public void onCommand(String command, String[] args) {


        try {
            PlayerUtils.sendMessage("");
            String owner = args[0];
            double posX = Double.valueOf(args[1]);
            double posY = Double.valueOf(args[2]);
            double posZ = Double.valueOf(args[3]);
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, HackeditemUtils.addSpawnerToFurnance(HackeditemUtils.generatetpsspawner(owner, posX, posY, posZ))));
            PlayerUtils.sendMessage(Msg);
        } catch (Exception eZ) {
            PlayerUtils.sendMessage( EnumChatFormatting.AQUA + "Fehler bitte überprüfe die eingabe");
        }
    }
}

