package me.bratwurst.module.modules.Crasher;

import me.bratwurst.event.EventTarget;
import me.bratwurst.event.events.EventMotionUpdate;
import me.bratwurst.event.events.ProcessPacketEvent;
import me.bratwurst.module.Category;
import me.bratwurst.module.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C12PacketUpdateSign;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S27PacketExplosion;

public class Opsign extends Module {
    public Opsign() {
        super("Opsign", Category.EXPLOIT);
    }
    @EventTarget
    public void ProcessPacketEvent(ProcessPacketEvent e) {
        Packet packet = e.getPacket();
        if (packet instanceof C12PacketUpdateSign) {
            C12PacketUpdateSign signPkt = (C12PacketUpdateSign)packet;
            e.setCancelled(true);
            e.setPacket(new C12PacketUpdateSign(signPkt.getPosition(), signPkt.getLines()));


        }
    }
}
