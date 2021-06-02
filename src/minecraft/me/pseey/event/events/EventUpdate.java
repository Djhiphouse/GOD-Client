package me.pseey.event.events;

import me.pseey.event.Event;

public class EventUpdate extends Event {

    Event.State state;

    public Event.State getState() {
        return state;
    }
}
