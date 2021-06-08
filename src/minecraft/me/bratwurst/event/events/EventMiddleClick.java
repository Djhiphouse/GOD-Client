package me.bratwurst.event.events;

import me.bratwurst.event.Event;
import net.minecraft.entity.EntityLivingBase;


public class EventMiddleClick extends Event {


    public EventMiddleClick(State single) {
        super();
    }
    @Override
    public boolean isPre() {
        return false;
    }
}
