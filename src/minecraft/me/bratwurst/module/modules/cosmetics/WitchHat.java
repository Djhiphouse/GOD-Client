package me.bratwurst.module.modules.cosmetics;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class WitchHat extends Module {
    public WitchHat() {
        super("WitchHat", Category.COSMETICS);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }
}
