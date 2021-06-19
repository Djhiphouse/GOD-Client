package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class flyitems extends Command {
    public flyitems() {
        super("flyitems", "flyitems", "flyitems:  lass items fleigen");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 0) {
            MainUtil.SendClientMesage(EnumChatFormatting.AQUA + "Bitte trage ein item Namen ein");
        } else {
            try {
                MainUtil.SendClientMesage(EnumChatFormatting.GREEN + "Erfolgreich");
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getSpawnerFromFurnace(FTools_ItemUtils.getFlyBlocks(args[0]))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("Fehler");
            }
        }
    }
}