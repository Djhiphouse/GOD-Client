package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class NameProtect extends Module {
    public NameProtect() {
        super("NameProtect", Category.EXPLOIT);
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

           for (Entity Targetname : Minecraft.getMinecraft().theWorld.loadedEntityList) {
               if (Targetname instanceof EntityPlayer && Targetname != null){
                   String text = mc.thePlayer.getName();
                   if (text.contains(mc.thePlayer.getName())) {
                       text = text.replaceAll(mc.thePlayer.getName(), "MeMe");
                   }
               }

           }

    }
}
