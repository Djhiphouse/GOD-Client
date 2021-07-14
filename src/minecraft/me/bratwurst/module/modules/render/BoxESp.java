package me.bratwurst.module.modules.render;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.FTools_RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class BoxESp extends Module {
    public BoxESp() {
        super("BoxESP", Category.RENDER);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer && o != null) {
                Entity entity = (Entity)o;
                FTools_RenderUtils.entityESPBox(entity, 0);
            }


        }

    }
}
