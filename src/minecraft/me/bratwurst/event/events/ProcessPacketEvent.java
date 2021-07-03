package me.bratwurst.event.events;

import me.bratwurst.event.Event;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;

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
    public void sendpacket(final  Packet packet ){
        this.packet = packet;
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(packet);

    }

    @Override
    public boolean isPre() {
        return false;
    }
}
