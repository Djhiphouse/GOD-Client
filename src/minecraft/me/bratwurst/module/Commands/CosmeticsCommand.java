package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

public class CosmeticsCommand extends Command {
    public CosmeticsCommand() {
        super("Cosmetics", "Cosmetics", "Schalte deine Cosmetics ein oder aus!");
    }
    public static boolean cos = true;

    @Override
    public void onCommand(String command, String[] args){
        if(args.length != 1){
            PlayerUtils.sendMessage(EnumChatFormatting.RED + "Nutze #Cosmetics on/off");
        }
        if (args[0].equalsIgnoreCase("on")){
            cos = true;
            PlayerUtils.sendMessage(EnumChatFormatting.GREEN + "Du hast deine Cosmetics eingeschaltet!");
        }else if(args[0].equalsIgnoreCase("off")){
            cos = false;
            PlayerUtils.sendMessage(EnumChatFormatting.RED + "Du hast deine Cosmetics ausgeschaltet!");
        }else {
            PlayerUtils.sendMessage(EnumChatFormatting.RED + "Nutze #Cosmetics on/off");
        }
    }
}
