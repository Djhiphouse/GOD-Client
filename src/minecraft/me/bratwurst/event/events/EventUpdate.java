package me.bratwurst.event.events;

import me.bratwurst.event.Event;

public class EventUpdate extends Event {

    Event.State state;

    public Event.State getState() {
        return state;
    }
}
