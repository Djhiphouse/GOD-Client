package me.bratwurst.event.events;

import me.bratwurst.event.Event;

public class RenderEvent extends Event
{
    @Override
    public boolean isPre() {
        return false;
    }
}