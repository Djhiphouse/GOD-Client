package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.lumien.chunkanimator.ChunkAnimator;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;

public class chunkAnimator extends Module {
    public chunkAnimator() {
        super("chunkAnimator", Category.RENDER);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {


    }

    @Override
    public void onEnable() {
        super.onEnable();
        ChunkAnimator.INSTANCE.onStart();
    }

    @Override
    public void onDisable() {
        super.onDisable();

    }
}
