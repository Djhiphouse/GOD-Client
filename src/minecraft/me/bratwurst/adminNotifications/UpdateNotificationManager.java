package me.bratwurst.adminNotifications;

import java.util.concurrent.LinkedBlockingDeque;

public class UpdateNotificationManager {
    private static LinkedBlockingDeque<UpdateNotification> pendingNotifications = new LinkedBlockingDeque<>();
    private static UpdateNotification currentNotification = null;

    public static void show(UpdateNotification updatenotification) {
        pendingNotifications.add(updatenotification);
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
