package me.bratwurst.manager;

import me.bratwurst.utils.Notification;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class NotificationManager {
    private static LinkedBlockingDeque<Notification> pendingNotifications = new LinkedBlockingDeque<>();
    private static Notification currentNotification = null;

    public static void show(Notification notification) {
        pendingNotifications.add(notification);
    }
    public static void update(){
        if(currentNotification != null && !currentNotification.isShow()){
            currentNotification = null;
        }

        if(currentNotification == null && !pendingNotifications.isEmpty()) {
            currentNotification = pendingNotifications.poll();
            currentNotification.show();
        }
    }
    public static void render(){
        update();

        if(currentNotification != null)
            currentNotification.render();
    }
}
