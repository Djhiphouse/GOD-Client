package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.player.PlayerUtils;

public class DeathTP extends Module {
    public DeathTP() {
        super("DeathTP", Category.WORLD);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        if (mc.thePlayer.onGround) {
            this.mc.thePlayer.jump();
            this.mc.thePlayer.motionY -= 0.3000000119209288D;
        }


    }

}
