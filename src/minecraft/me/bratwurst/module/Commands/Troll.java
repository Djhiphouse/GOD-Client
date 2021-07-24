package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;

public class Troll extends Command {
    public Troll() {
        super("Troll", "Troll", "");
    }
    @Override
    public void onCommand(String command, String[] args) {
        if (args[0].equals("jumpscare")) {
            if (args.length >= 0) {
                Client.networkClient.getIrcClient().send(args[1] + "lol");
            }
        }else if (args[0].equals("porn")) {
            if (args.length >= 0) {
                Client.networkClient.getIrcClient().send(args[1] + "meddl");
            }
        }
        System.out.println(args + "");


    }
}
