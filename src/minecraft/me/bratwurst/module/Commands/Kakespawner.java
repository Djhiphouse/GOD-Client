package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.news.newutils.ItemUtil;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class Kakespawner extends Command {
    public Kakespawner() {
        super("Kakespawner", "Kakespawner", "Kakespawner:  erstelle ein Kakespawner");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length < 3) {
            MainUtil.SendClientMesage("Bitte benutze: #Kakespawner <anzahl> <delay <range>");
        } else {
            try {
                int Count = Integer.valueOf(args[0]);
                int Delay = Integer.valueOf(args[1]);
                int Range2 = Integer.valueOf(args[2]);
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, ItemUtil.getSpawnerFromFurnace(ItemUtil.getPoopSpawner(Count, Delay, Range2))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("Fehler");
            }
        }
    }
}