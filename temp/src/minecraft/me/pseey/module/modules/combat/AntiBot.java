package me.pseey.module.modules.combat;

import de.Hero.settings.Setting;
import me.pseey.Client;
import me.pseey.event.EventTarget;
import me.pseey.event.events.EventMotionUpdate;
import me.pseey.module.Category;
import me.pseey.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.ArrayList;

public class AntiBot extends Module {

    public static ArrayList<EntityPlayer> bots = new ArrayList<>();
    public static Setting mode1;

    public AntiBot() {
        super("AntiBot", Category.COMBAT);
        ArrayList<String> options = new ArrayList<>();
        options.add("Checks");
        Client.setmgr.rSetting(mode1 = new Setting("AntiBot Mode", this, "Checks", options));
    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer bot = (EntityPlayer) o;
                if (bot != mc.thePlayer && bot != null) {
                    check(bot);
                }
            }
        }
    }

    public void check(EntityPlayer o) {
        if (o.groundTicks <= 72) {
            bots.add(o);
        } else {
            bots.remove(o);
        }
    }


}
