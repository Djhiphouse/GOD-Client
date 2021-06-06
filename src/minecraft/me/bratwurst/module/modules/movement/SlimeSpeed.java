package me.bratwurst.module.modules.movement;

import de.Hero.settings.Setting;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

import java.util.ArrayList;

public class SlimeSpeed extends Module {
    public Setting Speed, Bypass, TeleportDelay, TPRange;
    public static Setting mode1;
    public SlimeSpeed() {
        super("SlimeSpeed", Category.MOVEMENT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Test");

    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mode1.getValString().equalsIgnoreCase("Test")) {
            //hier wird auf gerufen
            Test();
        }
    }
    public void Test() {
        //hier der code

    }
}
