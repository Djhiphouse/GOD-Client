package me.bratwurst.module.modules.World;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class FastEat extends Module {
    public FastEat() {
        super("FastEat", Category.WORLD);
    }
    @EventTarget
    public void onUpdate(EventUpdate event) {
   if (this.mc.thePlayer.onGround && this.mc.thePlayer.getItemInUseDuration() >= 15 && this.mc.thePlayer.getItemInUse().getItem() instanceof net.minecraft.item.ItemFood) {
       for (byte b = 0; b <= 20; b = (byte)(b + 1))
           this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer(true));
   }
    }
}
