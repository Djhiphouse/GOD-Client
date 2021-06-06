package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.util.BlockPos;

public class AntiCobweb extends Module {
    public AntiCobweb() {
        super("AntiCobWeb", Category.MOVEMENT);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
         if(mc.thePlayer.isInWeb){
             mc.thePlayer.onGround = true;
             mc.thePlayer.setSpeed(0.75);
         }
    }
}
