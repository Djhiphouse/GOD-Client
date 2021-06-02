package me.bratwurst.module.modules.Crasher;

import io.netty.buffer.Unpooled;
import me.bratwurst.Client;
import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventUpdate;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import me.bratwurst.utils.TimeHelper;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class LabyFaker extends Module {
    public LabyFaker() {
        super("LabyFaker", Category.EXPLOIT);
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        final PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
        if (TimeHelper.hasReached(500)) {
            buffer.writeString("LMC");
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("LMC", buffer));
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("LabyMod", buffer));
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("Laby", buffer));
            Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C17PacketCustomPayload("LabyModClient", buffer));
        }


    }
}
