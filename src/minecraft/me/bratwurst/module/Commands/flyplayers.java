package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.news.newutils.ItemUtil;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class flyplayers extends Command {
    public flyplayers() {
        super("flyplayers", "flyplayers", "flyplayers: Lass gefakete spieler spawnen");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 0) {
            MainUtil.SendClientMesage(EnumChatFormatting.AQUA  + "Trage bitte denn spiler Namen ein");
        } else {
            try {
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, ItemUtil.getSpawnerFromFurnace(ItemUtil.getFlyPlayers(args[0]))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("fehler");
            }
        }
    }
}