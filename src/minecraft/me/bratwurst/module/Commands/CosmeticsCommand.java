package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.util.EnumChatFormatting;

public class CosmeticsCommand extends Command {
    public CosmeticsCommand() {
        super("Cosmetics", "Cosmetics", "Schalte deine Cosmetics ein oder aus!");
    }
    public static boolean cos = true;
    public static boolean win = true;
    public static boolean hat = true;

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
        }else if(args.length == 2){
            if(args[1].equalsIgnoreCase("wings") && args[0].equalsIgnoreCase("on")){
                win = true;
            }else if(args[1].equalsIgnoreCase("wings") && args[0].equalsIgnoreCase("off")){
                win = false;
            } else if(args[1].equalsIgnoreCase("hat") && args[0].equalsIgnoreCase("on")){
                hat = true;
            }else if(args[1].equalsIgnoreCase("hat") && args[0].equalsIgnoreCase("off")) {
                hat = false;
            }else{
                PlayerUtils.sendMessage(EnumChatFormatting.RED + "Nutze #Cosmetics on/off (hat/wings)");
            }

        }
    }
}
