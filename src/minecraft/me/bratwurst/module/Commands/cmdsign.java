package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.news.newutils.ItemUtil;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class cmdsign extends Command {
    public cmdsign() {
        super("cmdsign", "cmdsign", "cmdsign: setze denn command fest für ein clickschild");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 0) {
            MainUtil.SendClientMesage(EnumChatFormatting.AQUA + "itte Trage ein Command ein");
        } else {
            mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, ItemUtil.getCommandSign(String.join((CharSequence)" ", args), "[Click]")));
        }
    }
}
