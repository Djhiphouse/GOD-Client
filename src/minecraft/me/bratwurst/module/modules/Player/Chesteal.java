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
    public static Setting  Delay,AutoClose,SlietCheast;

    public Chesteal() {
        super("Chesteal", Category.PLAYER);
    }

    @Override
    public void setup() {
       Client.setmgr.rSetting(Delay = new Setting("Delay", this, 50, 1, 400, true));
        Client.setmgr.rSetting(AutoClose = new Setting("AutoClose", this, true));

    }

    @EventTarget
    public void onUpdate(EventMotionUpdate e) {
        Chest();

    }
public static boolean Slient = false;
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
            if (isChestEmpty(container) && AutoClose.getValBoolean()) {
                this.mc.thePlayer.closeScreen();
                this.mc.updateDisplay();
                this.mc.displayGuiScreen(null);
            }
        }
    }
    public static boolean isChestEmpty(ContainerChest chest) {
        for (int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); i++) {
            if (chest.getLowerChestInventory().getStackInSlot(i) != null)
                return false;
        }
        return true;
    }

}