package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class bugitem extends Command {
    public bugitem() {
        super("bugitem", "bugitem", "bugitem: gebe dir ein Bugitem");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 0) {
           MainUtil.SendClientMesage(EnumChatFormatting.AQUA + "Bitte Trage ein Itemnamen ein");
        } else {
            try {
                bugitem.mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getBugItem(args[0])));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage(EnumChatFormatting.RED + "Fehler beim erstellen des Items");
            }
        }
    }
}
