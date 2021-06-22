package me.bratwurst.module.Commands;


import me.bratwurst.manager.Command;
import me.bratwurst.news.newutils.ItemUtil;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class blockring extends Command {
    public blockring() {
        super("blockring", "blockring", "blockring:  erstelle ein Blockring");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length < 2) {
            MainUtil.SendClientMesage("Bitte Trage: #blockring <block> <height>");
        } else {
            try {
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, ItemUtil.getSpawnerFromFurnace(ItemUtil.getBlockRing(args[0], Integer.parseInt(args[1])))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("Der Block wurde nicht gefunden");
            }
        }
    }
}