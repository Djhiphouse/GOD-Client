package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class destroyblock extends Command {
    public destroyblock() {
        super("destroyblock", "destroyblock", "destroyblock: Lass denn block an deinen codrs zerstören");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length < 3) {
            MainUtil.SendClientMesage("Bitte benutze: #destroyblock <x> <y> <z>");
        } else {
            try {
               mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getSpawnerFromFurnace(FTools_ItemUtils.getDestroyBlock(Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2])))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("Fehler");
            }
        }
    }
}
