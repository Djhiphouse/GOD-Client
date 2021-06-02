package me.bratwurst.event.events;

import me.bratwurst.event.Event;
import net.minecraft.network.Packet;

public class ProcessPacketEvent extends Event {
    Packet packet;
    public ProcessPacketEvent(Packet packet) {
        this.packet = packet;


    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

}
