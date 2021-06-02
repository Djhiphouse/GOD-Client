package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.manager.TimeHelper;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;

public class WaterSpeed extends Module {
    public WaterSpeed() {
        super("WaterSpeed", Category.MOVEMENT);
    }
    @EventTarget
    public void onUpdate(EventUpdate e) {

    }
}
