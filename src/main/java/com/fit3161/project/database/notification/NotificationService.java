package com.fit3161.project.database.notification;

import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.tasks.TaskRecord;
import com.fit3161.project.database.user.UserRecord;

import java.util.List;
import java.util.function.Consumer;

public interface NotificationService {

    NotificationRepository getNotificationRepository();

    default NotificationRecord createNotification(Consumer<NotificationRecord.NotificationRecordBuilder> consumer) {
        final NotificationRecord.NotificationRecordBuilder notificationBuilder = new NotificationRecord.NotificationRecordBuilder();
        consumer.accept(notificationBuilder);
        return getNotificationRepository().save(notificationBuilder.build());
    }

    default void deleteNotification(NotificationRecord notificationRecord) {
        getNotificationRepository().delete(notificationRecord);
    }

    default List<NotificationRecord> findAllNotifications() {
        return (List<NotificationRecord>) getNotificationRepository().findAll();
    }

    default UserRecord findUserByNotification(NotificationRecord record) {
        return getNotificationRepository().findUserByNotificationId(record.getNotificationId());
    }

    default EventRecord findEventByNotification(NotificationRecord record) {
        return getNotificationRepository().findEventByNotificationId((record.getNotificationId()));
    }

    default TaskRecord findTaskByNotification(NotificationRecord record) {
        return getNotificationRepository().findTaskByNotificationId((record.getNotificationId()));
    }

    default NotificationRecord findNotification(EventRecord eventRecord) {
        return getNotificationRepository().findNotificationRecordByEvent(eventRecord);
    }

    default NotificationRecord findNotification(TaskRecord taskRecord) {
        return getNotificationRepository().findNotificationRecordByTask(taskRecord);
    }

    default void updateNotification(NotificationRecord notificationRecord) {
        getNotificationRepository().save(notificationRecord);
    }
}




