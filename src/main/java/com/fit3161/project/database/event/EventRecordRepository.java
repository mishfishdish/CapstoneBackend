package com.fit3161.project.database.event;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventRecordRepository extends CrudRepository<EventRecord, UUID> {
    EventRecord findEventRecordByEventId(UUID eventId);
}
