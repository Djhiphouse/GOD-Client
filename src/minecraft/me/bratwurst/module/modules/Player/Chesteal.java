package me.bratwurst.module.modules.Player;

import de.Hero.settings.Setting;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;

public class Chesteal extends Module {
    public static Setting mode1;
    public static Setting  Delay;

    public Chesteal() {
        super("Chesteal", Category.PLAYER);
    }

    @Override
    public void setup() {
       Client.setmgr.rSetting(Delay = new Setting("Delay", this, 50, 1, 400, true));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        Chest();

    }

    public void Chest() {
        if (mc.thePlayer.openContainer != null && mc.thePlayer.openContainer instanceof ContainerChest) {
            ContainerChest container = (ContainerChest) mc.thePlayer.openContainer;
            if (!container.getLowerChestInventory().getName().contains("Chest")) {
                return;
            }
            for (int i = 0; i < container.getLowerChestInventory().getSizeInventory(); i++) {
                if (container.getLowerChestInventory().getStackInSlot(i) != null) {
                    if (TimeHelper.hasReached(Delay.getValInt())) {
                        mc.playerController.windowClick(container.windowId, i, 0, 1, mc.thePlayer);
                        TimeHelper.reset();

                    }
                }
            }
        }
    }

}