package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class FullBright extends Module {
    public float gamaSettings;
    public FullBright() {
        super("FullBright", Category.RENDER);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }

    @Override
    public void onDisable() {
        this.mc.gameSettings.gammaSetting = this.gamaSettings;

    }

    @Override
    public void onEnable() {
        this.gamaSettings = this.mc.gameSettings.gammaSetting;
        this.mc.gameSettings.gammaSetting = 100.0F;

    }
}
