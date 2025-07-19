package com.fit3161.project.database.notification;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NotificationRepository extends CrudRepository<NotificationRecord, UUID> {

    NotificationRecord findNotificationRecordByEvent(EventRecord eventRecord);

}
