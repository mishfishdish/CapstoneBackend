package com.fit3161.project.database.notification;

import com.fit3161.project.database.event.EventClubRepository;
import com.fit3161.project.database.event.EventDependencyRepository;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.event.EventRecordRepository;

import java.util.function.Consumer;

public interface NotificationService {

    NotificationRepository getNotificationRepository();

    default NotificationRecord createNotification(Consumer<NotificationRecord.NotificationRecordBuilder> consumer){
        final NotificationRecord.NotificationRecordBuilder notificationBuilder = new NotificationRecord.NotificationRecordBuilder();
        consumer.accept(notificationBuilder);
        return getNotificationRepository().save(notificationBuilder.build());
    }


}