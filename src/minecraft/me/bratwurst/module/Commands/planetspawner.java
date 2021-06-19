package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.FTools_ItemUtils;
import me.bratwurst.utils.MainUtil;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.util.EnumChatFormatting;

public class planetspawner extends Command {
    public planetspawner() {
        super("planetspawner", "planetspawner", "planetspawner: Gib dir ein Planetspawner");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 0) {
            MainUtil.SendClientMesage(EnumChatFormatting.AQUA + "Bitte trage eine spawnrange ein!");
        } else {
            try {
                mc.thePlayer.sendQueue.addToSendQueue(new C10PacketCreativeInventoryAction(36, FTools_ItemUtils.getSpawnerFromFurnace(FTools_ItemUtils.getPlanetSpawner(Integer.parseInt(args[0])))));
            }
            catch (Exception e) {
                MainUtil.SendClientMesage("Fehler");
            }
        }
    }
}
