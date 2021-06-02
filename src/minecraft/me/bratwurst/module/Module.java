package me.bratwurst.module;

import me.bratwurst.Client;
import me.bratwurst.manager.ModuleManager;
import me.bratwurst.manager.NotificationManager;
import me.bratwurst.utils.Notification;
import me.bratwurst.utils.NotificationType;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;

public class Module {

    public static Minecraft mc = Minecraft.getMinecraft();
    public String name;
    public boolean toggle;
    public int key;
    public Category category;

    public void setup() { }

    public Module(String name, int key, Category category) {
        this.name = name;
        this.key = key;
        this.category = category;
        setup();
    }

    public Module(String name, Category category) {
        this.name =  name;
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
        if(toggle) {
            onEnable();
            if(!getName().equalsIgnoreCase("clickgui"))
                NotificationManager.show(new Notification(NotificationType.INFO, EnumChatFormatting.GREEN + "Aktiviert", EnumChatFormatting.GOLD + this.getName(),1));
        }
        else {
            onDisable();
            if(!getName().equalsIgnoreCase("clickgui"))
                NotificationManager.show(new Notification(NotificationType.INFO, EnumChatFormatting.RED + "Deaktiviert", EnumChatFormatting.GOLD + this.getName(), 1));
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
