package me.bratwurst.manager;


import me.bratwurst.module.Commands.*;
import me.bratwurst.utils.player.PlayerUtils;

import java.util.ArrayList;

public class CommandManager {

    public ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        load();

    }

    public void reset() {
        for (int i = 0; i < commands.size(); i++) {
            commands.remove(i);
        }

    }

    public void load() {
        commands.add(new AddFriend());
        commands.add(new AdminEvilHack());
        commands.add(new Banbook());
        commands.add(new Beleidigen());
        commands.add(new Holo());
        commands.add(new kickadmin());
        commands.add(new NBT_Crasher());
        commands.add(new OpBook());
        commands.add(new Spawngriefer());
        commands.add(new Spawnnuke());
        commands.add(new Trustbook());
        commands.add(new SpawnTittel());
        commands.add(new Tpspawner());
        commands.add(new Config());
        commands.add(new Pluginscmd());
        commands.add(new AdminGui());
        commands.add(new TrollPotion());
        commands.add(new SchwertCommand());
        commands.add(new Login());
        commands.add(new SpielerWirbler());
        commands.add(new KillPotion());
        commands.add(new Checkcmd());
        commands.add(new CrashTag());
        commands.add(new opzombie());
        commands.add(new Cmdregner());
        commands.add(new fakegm());
        commands.add(new copyitemnbt());
        commands.add(new Gethwid());
        commands.add(new Ingamename());
        commands.add(new irc());
        commands.add(new BanCommand());
        commands.add(new Rundruf());
//        commands.add(new BackdoorCommand());
        commands.add(new CosmeticsCommand());
        commands.add(new UpdateCommand());
        commands.add(new blockring());
        commands.add(new BookPage());
        commands.add(new cmdsign());
        commands.add(new flyblocks());
        commands.add(new flyitems());
        commands.add(new flyitems());
        commands.add(new Kakespawner());
        commands.add(new Penisbombe());
        commands.add(new planetspawner());
        commands.add(new BildHologram());
        commands.add(new instantcrash());
        commands.add(new imghologram());
    




    }

    public void call(String input) {
        String[] split = input.split(" ");
        String commandName = split[0];
        String args = input.substring(commandName.length()).trim();
        for (Command c : commands) {
            if (c.getName().equalsIgnoreCase(commandName) || c.getAlias().equalsIgnoreCase(commandName)) {
                c.onCommand(args, args.split(" "));
                return;
            }
        }
        PlayerUtils.sendMessage(" Command Not Found!", true);
        PlayerUtils.sendMessage(" ", false);
        for (Command c : commands) {
            PlayerUtils.sendMessage(" " + c.getSyntax(), true);
        }
    }
}
