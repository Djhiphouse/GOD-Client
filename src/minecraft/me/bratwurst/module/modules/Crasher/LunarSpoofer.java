package me.bratwurst.module.modules.Crasher;

import io.netty.buffer.Unpooled;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class LunarSpoofer extends Module {
    public LunarSpoofer() {
        super("LunarSpoofer", Category.EXPLOIT);
    }
    @EventTarget
    public void onUpdate(EventMotionUpdate e) {

    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString("Lunar|LC")));
    }
}
