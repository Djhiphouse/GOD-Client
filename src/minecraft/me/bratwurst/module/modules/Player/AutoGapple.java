package me.bratwurst.module.modules.Player;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class AutoGapple extends Module {
    public AutoGapple() {
        super("AutoGapple", Category.PLAYER);
    }
    @EventTarget

    public void onUpdate(EventMotionUpdate e) {
        if (mc.thePlayer.getHeldItem().getItem().equals(Items.iron_sword) && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.hurtTime <= 13) {


        }
    }
}
