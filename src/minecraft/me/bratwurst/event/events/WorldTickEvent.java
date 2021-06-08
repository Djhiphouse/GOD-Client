package me.bratwurst.event.events;

import me.bratwurst.event.Event;

public class WorldTickEvent extends Event
{
    @Override
    public boolean isPre() {
        return false;
    }
}
