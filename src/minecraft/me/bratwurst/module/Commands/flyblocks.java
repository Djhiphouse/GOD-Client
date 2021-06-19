package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;

public class flyblocks extends Command {
    public flyblocks() {
        super("flyblocks", "flyblocks", "flyblocks: setze lass blöcke fleigen");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 0) {
            MainUtil.SendClientMesage("Bitte trage ein block Namen ein");
        } else {
            try {
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getSpawnerFromFurnace(FTools_ItemUtils.getFlyBlocks(args[0]))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("An error has occurred");
            }
        }
    }
}
