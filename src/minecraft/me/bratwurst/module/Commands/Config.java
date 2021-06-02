package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.manager.ConfigManager;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

public class Config extends Command {
    public Config() {
        super("Config", "Config", "Lade eine config");
    }

    @Override
    public void onCommand(String command, String[] args) {
        if (args.length == 2) {

                if (args[0].equalsIgnoreCase("save")) {
                    ConfigManager.addConfig(args[1]);
                }else if (args[0].equalsIgnoreCase("load")) {
                    ConfigManager.loadConfig(args[1]);
                }else if (args[0].equalsIgnoreCase("remove")) {
                    ConfigManager.removeConfig(args[1]);
                }
              PlayerUtils.sendMessage(EnumChatFormatting.AQUA + "Deine Config: "+EnumChatFormatting.YELLOW + args[1]+ EnumChatFormatting.AQUA+ " Wurde gespeichert");

        }

    }
}
