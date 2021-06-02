package me.bratwurst.adminNotifications;


import java.util.concurrent.LinkedBlockingDeque;

public class AdminNotificationManager {
    private static LinkedBlockingDeque<AdminNotification> pendingNotifications = new LinkedBlockingDeque<>();
    private static AdminNotification currentNotification = null;

    public static void show(AdminNotification adminNotification) {
        pendingNotifications.add(adminNotification);
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
