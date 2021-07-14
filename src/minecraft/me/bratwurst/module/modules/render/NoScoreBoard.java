package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class NoScoreBoard extends Module {
    public NoScoreBoard() {
        super("NoScoreBoard", Category.RENDER);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }
}
