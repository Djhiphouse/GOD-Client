package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class AutoRespawn extends Module {
    public AutoRespawn() {
        super("AutoRespwan", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        mc.thePlayer.respawnPlayer();
    }

}
