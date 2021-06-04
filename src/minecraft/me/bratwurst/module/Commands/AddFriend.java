package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Arrays;
import java.util.HashSet;

public class AddFriend extends Command {
    private final HashSet<String> hashSet = new HashSet<>();
    private static final AddFriend instance = new AddFriend();
    public AddFriend() {
        super("Freund", "Freund", "Freund");
    }

    @Override
    public void onCommand(String command, String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println("Args0: " + args[0]);

        System.out.println(Arrays.toString(args));
        System.out.println("Args0: " + args[0]);

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "add":
                    for (Entity TargetName : Minecraft.getMinecraft().theWorld.loadedEntityList) {
                        if (TargetName instanceof EntityPlayer && TargetName != mc.thePlayer) {
                            if (TargetName.getDistanceToEntity(mc.thePlayer) <= 5) {
                                String freund = TargetName.getName();
                                FreundManager.getInstance().addFriend(freund);
                                PlayerUtils.sendMessage(freund);
                            }else{
                                PlayerUtils.sendMessage("Du musst in der nähe sein!");
                            }
                        }
                    }

                    break;
                case "remove":


                    break;
                default:
                    PlayerUtils.sendMessage("#addfriend [add/remove] [name]");
            }
        }

    }
}
