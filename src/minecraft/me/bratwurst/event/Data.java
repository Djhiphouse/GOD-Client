package me.bratwurst.event;

import java.lang.reflect.Method;

/**
 * Created by Hexeption on 18/12/2016.
 */
public class Data {

    public Object source;

    public Method target;

    public byte priority;

    Data(Object source, Method target, byte priority) {

        this.source = source;
        this.target = target;
        this.priority = priority;
    }

}
