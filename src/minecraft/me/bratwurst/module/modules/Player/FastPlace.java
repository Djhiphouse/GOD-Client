package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Random;

public class FastPlace extends Module {
    public Setting Delay, BypassHypixel, Normal, MoveFix;
    public static Setting mode1;
    public FastPlace() {
        super("FastPlace", Category.PLAYER);
        ArrayList<String> options = new ArrayList<>();
        options.add("Mineplex");
        options.add("Hypixel");
        options.add("Normal");
        Client.setmgr.rSetting(mode1 = new Setting("FastPlace Mode", this, "Normal", options));
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(BypassHypixel = new Setting("HypixelBypass", this, true));
        Client.setmgr.rSetting(Delay = new Setting("Delay", this, 0, 0, 3, true));

    }

    @EventTarget
    public void onUpdate(EventUpdate e) {


        if (mode1.getValString().equalsIgnoreCase("Hypixel")) {
            Hypixel();
        }else if (mode1.getValString().equalsIgnoreCase("Normal")) {
            Normal(Delay.getValInt());
        }

    }


    public void Hypixel() {

        mc.rightClickDelayTimer = 2;

    }


    public void Normal(int delay) {
        mc.rightClickDelayTimer = delay;
    }


}
