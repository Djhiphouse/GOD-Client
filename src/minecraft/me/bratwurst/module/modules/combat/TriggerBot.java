package me.bratwurst.module.modules.combat;

import de.Hero.clickgui.elements.ModuleButton;
import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.manager.FreundManager;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;

public class TriggerBot extends Module {
    public static Setting mode1;
    private ModuleButton mb = null;
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

        if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityPlayer) {
            mc.rightClickMouse();

        }
    }
}
