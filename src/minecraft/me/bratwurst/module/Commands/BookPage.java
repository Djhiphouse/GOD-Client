package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class BookPage extends Command {
    public BookPage() {
        super("BookPage", "BookPage", "BookPage: setze denn command fest");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length < 2) {
            MainUtil.SendClientMesage("Trage bitte Buchseiten anzahl ein und Message/command");
        } else {
            try {
                String commandMessage = "";
                for (int i = 1; i < args.length; ++i) {
                    commandMessage = commandMessage + args[1] + " ";
                }
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getBookPageExploit(Integer.parseInt(args[0]), commandMessage.trim())));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("Ein Fehler ist aufgetretten");
            }
        }
    }
}
