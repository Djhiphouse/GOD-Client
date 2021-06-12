package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class FastBreak extends Module {
    public FastBreak() {
        super("FastBreak", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate event) {
        this.mc.thePlayer.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 15, 3));
    }
}
