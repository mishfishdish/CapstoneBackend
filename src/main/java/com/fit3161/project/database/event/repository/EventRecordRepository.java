package com.fit3161.project.database.event.repository;

import com.fit3161.project.database.event.daos.EventDependencies;
import com.fit3161.project.database.event.daos.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventRecordRepository extends CrudRepository<EventRecord, UUID> {
}
