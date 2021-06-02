package me.bratwurst.module.modules.combat;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Fastbow extends Module {
    public Fastbow() {
        super("Fastbow", Category.COMBAT);
    }
    @EventTarget

    public void onUpdate(EventUpdate event) {
        if (mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof net.minecraft.item.ItemBow  && mc.thePlayer.isUsingItem()) {
            mc.rightClickDelayTimer = 1;
            for (int i = 0; i < 20; i++)
                mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(true));
            mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
            mc.playerController.onStoppedUsingItem((EntityPlayer)mc.thePlayer);
        }
    }
}
