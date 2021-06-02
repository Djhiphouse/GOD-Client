package me.bratwurst.module.Commands;

import me.bratwurst.Client;
import me.bratwurst.manager.Command;
import net.minecraft.client.Minecraft;

public class Pluginscmd extends Command {
    public Pluginscmd() {
        super("Pl", "Plugins", "lass dir alle Plugins anzeigen");
    }
    @Override
    public void onCommand(String command, String[] args) {
        Client.getInstance().getModuleManager().getModuleByName("Plugins").toggle();
    }
}
