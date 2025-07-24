package com.fit3161.project.database.notification;

import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.tasks.TaskRecord;
import com.fit3161.project.database.user.UserRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface NotificationRepository extends CrudRepository<NotificationRecord, UUID> {

    NotificationRecord findNotificationRecordByEvent(EventRecord eventRecord);

    NotificationRecord findNotificationRecordByTask(TaskRecord eventRecord);

    @Query("SELECT n.userId FROM NotificationRecord n WHERE n.notificationId = :notificationId")
    UserRecord findUserByNotificationId(@Param("notificationId") UUID notificationId);

    @Query("SELECT n.event FROM NotificationRecord n WHERE n.notificationId = :notificationId")
    EventRecord findEventByNotificationId(@Param("notificationId") UUID notificationId);

    @Query("SELECT n.task FROM NotificationRecord n WHERE n.notificationId = :notificationId")
    TaskRecord findTaskByNotificationId(@Param("notificationId") UUID notificationId);

}
