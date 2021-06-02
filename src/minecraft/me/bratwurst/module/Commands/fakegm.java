package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C10PacketCreativeInventoryAction;
import net.minecraft.world.WorldSettings;

public class fakegm extends Command {
    public fakegm() {
        super("fakegm", "fakegm", "fakegm: setzte dich in fake gamemode");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (args.length <= 1) {
            String mode;
            String str1;
            switch ((str1 = mode = args[0]).hashCode()) {
                case 48:
                    if (!str1.equals("0"))
                        break;
                    (Minecraft.getMinecraft()).playerController.setGameType(WorldSettings.GameType.SURVIVAL);
                    break;
                case 49:
                    if (!str1.equals("1"))
                        break;
                    (Minecraft.getMinecraft()).playerController.setGameType(WorldSettings.GameType.CREATIVE);
                    break;
                case 50:
                    if (!str1.equals("2"))
                        break;
                    (Minecraft.getMinecraft()).playerController.setGameType(WorldSettings.GameType.ADVENTURE);
                    break;
                case 51:
                    if (!str1.equals("3"))
                        break;
                    (Minecraft.getMinecraft()).playerController.setGameType(WorldSettings.GameType.SPECTATOR);
                    break;
            }
            if (!mode.equals("0") && !mode.equals("1") && !mode.equals("2") && !mode.equals("3"))
               PlayerUtils.sendMessage("<0/1/2/3>");
            PlayerUtils.sendMessage("bist nun im + this.mc.playerController.func_178889_l().toString() + " );
        } else {
            PlayerUtils.sendMessage("<0/1/2/3>");
        }
    }
}