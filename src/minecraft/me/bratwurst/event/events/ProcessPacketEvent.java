package me.bratwurst.event.events;

import me.bratwurst.event.Event;
import net.minecraft.network.Packet;

public class ProcessPacketEvent extends Event {
    private Packet packet;

    public ProcessPacketEvent(final Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public void setPacket(final Packet packet) {
        this.packet = packet;
    }

}
