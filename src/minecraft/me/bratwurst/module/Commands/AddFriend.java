package me.bratwurst.module.Commands;

import me.bratwurst.manager.Command;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.utils.player.PlayerUtils;

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
                    PlayerUtils.sendMessage("Du hast " + args[1] + " zu deinen Freunden hinzugefügt!");
                    FreundManager.getInstance().addFriend(args[1]);
                    break;
                case "remove":
                    PlayerUtils.sendMessage("Du hast " + args[1] + "  von deinen Freunden entfernt!");
                    FreundManager.getInstance().removeFriend(args[1]);
                    break;
                default:
                    PlayerUtils.sendMessage("#addfriend [add/remove] [name]");
            }
        }

    }
}
