package me.bratwurst.module.modules.combat;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class TriggerBot extends Module {
    public static Setting mode1;
    public static EntityLivingBase target1;
    public static Setting minCps, maxCps, Range, FailHits, FakeLag;
    public TriggerBot() {
        super("TriggerBot", Category.COMBAT);
    }
    @Override
    public void setup() {

        Client.setmgr.rSetting(Range = new Setting("Range", this, 3.8, 1, 8, false));

    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        for (Object o : mc.theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                EntityPlayer target = (EntityPlayer) o;
                if (target != mc.thePlayer && target != null) {
                    String tname = target.getName();
                    if (target.getDistanceToEntity(mc.thePlayer) <= Range.getValDouble()
                            && !FreundManager.getInstance().isFriend(tname) && mc.objectMouseOver != null&& mc.objectMouseOver.entityHit != null) {
                        Attack(Range.getValInt());

                    }
                }
            }
        }
    }
    public void Attack(int Range) {
        mc.playerController.attackEntity(mc.thePlayer,target1);
        mc.thePlayer.swingItem();
    }
}
