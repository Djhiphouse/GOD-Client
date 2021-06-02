package me.pseey.module;

import me.pseey.Client;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class Module {

    public static Minecraft mc = Minecraft.getMinecraft();
    public String name;
    public boolean toggle;
    public int key;
    public Category category;

    public void setup() {
    }

    public Module(String name, int key, Category category) {
        this.name = name;
        this.key = key;
        this.category = category;
        setup();
    }

    public Module(String name, Category category) {
        this.name = name;
        this.key = 0;
        this.category = category;
        setup();
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    public void toggle() {
        toggle = !toggle;
        if (toggle) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public void onEnable() {
        Client.getInstance().getEventManager().register(this);
    }

    public void onDisable() {
        Client.getInstance().getEventManager().unregister(this);
    }


    public boolean isToggle() {
        return toggle;
    }

    public boolean isEnabled() {
        return toggle;
    }

}
