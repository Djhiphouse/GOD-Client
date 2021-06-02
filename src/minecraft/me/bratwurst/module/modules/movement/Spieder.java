package me.bratwurst.module.modules.movement;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;

public class Spieder extends Module {
    public Spieder() {
        super("Spider", Category.MOVEMENT);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
        if (mc.thePlayer.isCollidedHorizontally && Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown()) {
            this.mc.thePlayer.jump();
        }

    }
}
