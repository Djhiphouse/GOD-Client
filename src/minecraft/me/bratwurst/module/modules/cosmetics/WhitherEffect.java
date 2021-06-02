package me.bratwurst.module.modules.cosmetics;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class WhitherEffect extends Module {
    public WhitherEffect() {
        super("WhitherEffect", Category.COSMETICS);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }
}
