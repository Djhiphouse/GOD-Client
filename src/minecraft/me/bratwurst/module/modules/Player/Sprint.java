package me.bratwurst.module.modules.Player;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", Category.PLAYER);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        mc.thePlayer.setSprinting(true);
    }

}
