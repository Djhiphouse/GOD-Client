package me.bratwurst.event;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Hexeption on 18/12/2016.
 */
public abstract class Event {



    private boolean cancelled;

    public abstract boolean isPre();

    public enum State {
        PRE("PRE", 0),

        POST("POST", 1);

        private State(final String string, final int number) {

        }
    }


    public  Event call() {
        this.cancelled = false;
        if(this != null) {
            call(this);
        }
        return this;
    }

    public boolean isCancelled() {

        return cancelled;
    }

    public void setCancelled(boolean cancelled) {

        this.cancelled = cancelled;
    }

    public static void call(Event event) {
        if(event == null)return;
        ArrayHelper<Data> dataList = EventManager.get(event.getClass());

        if (dataList != null) {
            for (Data data : dataList) {

                try {
                    if(data != null && data.source != null && event != null && event.getClass() != null && dataList != null) {
                        data.target.invoke(data.source, event);
                    }
                } catch (IllegalAccessException e) {

                } catch (InvocationTargetException e) {

                }

            }
        }
    }
}
