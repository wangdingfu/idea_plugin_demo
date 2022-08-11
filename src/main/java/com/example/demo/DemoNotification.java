package com.example.demo;

import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;


public class DemoNotification {

    private static final NotificationGroup NOTIFICATION_GROUP = NotificationGroupManager.getInstance()
            .getNotificationGroup("demo_notify_group");


    public static void notifyWarn(String message, Project project) {
        NOTIFICATION_GROUP.createNotification(message, NotificationType.WARNING).notify(project);
    }

    public static void notifyInfo(String message, Project project) {
        NOTIFICATION_GROUP.createNotification(message, NotificationType.INFORMATION).notify(project);
    }

    public static void notifyError(String message, Project project) {
        NOTIFICATION_GROUP.createNotification(message, NotificationType.ERROR).notify(project);
    }
}
