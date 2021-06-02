package me.bratwurst.manager;

import net.minecraft.client.Minecraft;

public class Command {

    String name;
    String syntax;
    String Alias;

    public Command(String alias, String name, String syntax) {
        this.name = name;
        this.Alias = alias;
        this.syntax = syntax;
    }

    public Command(String name, String syntax) {
        this.name = name;
        this.Alias = name;
        this.syntax = syntax;
    }

    public static Minecraft mc = Minecraft.getMinecraft();
    public String getName() {
        return name;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSyntax() {
        return syntax;
    }

    public void onCommand(String command, String[] args) {

    }

}
