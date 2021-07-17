package me.bratwurst.event.events;

import me.bratwurst.event.Event;
import net.minecraft.client.Minecraft;

public class MoveEvent extends Event {
    public double x, y, z;

    boolean cancelled;

    public MoveEvent(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean isPre() {
        return false;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean state) {
        this.cancelled = state;
    }
}