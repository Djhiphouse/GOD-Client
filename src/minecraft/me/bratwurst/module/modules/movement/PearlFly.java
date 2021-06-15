package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.MainUtil;
import me.bratwurst.utils.MovementUtils;
import me.bratwurst.utils.RenderUtil;
import me.bratwurst.utils.player.PlayerUtils;

public class PearlFly extends Module {
    float oldPitch;
    boolean hasPearled;
    boolean dmaage = false;

    public PearlFly() {
        super("PearlFly", Category.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        if (!this.hasPearled) {
            event.setPitch(90f);
            if (PlayerUtils.isHoldingPearl()) {
                event.setPitch(90f);
                mc.thePlayer.rotationPitch = 90f;

                this.mc.playerController.sendUseItem(this.mc.thePlayer, this.mc.theWorld, this.mc.thePlayer.inventory.getCurrentItem());
                mc.thePlayer.rotationPitch = 90f;
                mc.thePlayer.rotationPitch = 90f;
                mc.thePlayer.rotationPitch = 90f;
                mc.thePlayer.rotationPitch = 90f;


            }

            this.hasPearled = true;

        }
        if (mc.thePlayer.hurtTime > 0.1) {
            dmaage = true;
            mc.thePlayer.rotationPitch = -3f;
            mc.thePlayer.rotationPitch = -3f;
            mc.thePlayer.rotationPitch = -3f;
            mc.thePlayer.rotationPitch = -3f;
        }
        if (dmaage == true) {

            this.mc.thePlayer.motionY = 0.0;
            if (MovementUtils.isMoving()) {
                this.mc.thePlayer.setSpeed(0.7f);
            }
            RenderUtil.bob(4.0f, 4.0f);
        }

    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.hasPearled = false;
         dmaage = false;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.mc.thePlayer.motionX = 0.0;
        this.mc.thePlayer.motionZ = 0.0;
        oldPitch = this.mc.thePlayer.rotationPitch;
    }
}


