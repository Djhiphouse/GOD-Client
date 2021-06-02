package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class HitBox extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting Breite, Hoehe, Range, FailHits, Rotate,AutoBlock,NoRotate,LegitAutoBlock,Movefix,Smoth;
    public HitBox() {
        super("HitBox", Category.COMBAT);
    }
    @Override
    public void setup() {
        Client.setmgr.rSetting(Breite = new Setting("Breite", this, 2, 1, 6, false));
        Client.setmgr.rSetting(Hoehe = new Setting("Hoehe", this, 2, 1, 6, false));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Entity o : this.mc.theWorld.loadedEntityList) {
            if (o instanceof net.minecraft.entity.player.EntityPlayer){
                if (o != mc.thePlayer) {
                    o.setSize(Breite.getValInt(), Hoehe.getValInt());
                }
            }

        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for (Entity e : this.mc.theWorld.loadedEntityList) {
            if (e instanceof net.minecraft.entity.player.EntityPlayer && e != this.mc.thePlayer)
                e.setSize(0.8F, 1.8F);
        }
    }
}
