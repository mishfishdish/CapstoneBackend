package com.fit3161.project.database.notification;

import com.fit3161.project.database.event.EventClubRepository;
import com.fit3161.project.database.event.EventDependencyRepository;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.event.EventRecordRepository;
import com.fit3161.project.database.tasks.TaskRecord;

import java.util.function.Consumer;

public interface NotificationService {

    NotificationRepository getNotificationRepository();

    default NotificationRecord createNotification(Consumer<NotificationRecord.NotificationRecordBuilder> consumer){
        final NotificationRecord.NotificationRecordBuilder notificationBuilder = new NotificationRecord.NotificationRecordBuilder();
        consumer.accept(notificationBuilder);
        return getNotificationRepository().save(notificationBuilder.build());
    }

    default void deleteNotification(NotificationRecord notificationRecord){
        getNotificationRepository().delete(notificationRecord);
    }

    default NotificationRecord findNotification(EventRecord eventRecord){
        return getNotificationRepository().findNotificationRecordByEvent(eventRecord);
    }

    default NotificationRecord findNotification(TaskRecord taskRecord){
        return getNotificationRepository().findNotificationRecordByTask(taskRecord);
    }

    default void updateNotification(NotificationRecord notificationRecord){
        getNotificationRepository().save(notificationRecord);
    }}


