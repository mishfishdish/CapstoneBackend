package com.fit3161.project.database.tasks;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskRecordRepository extends CrudRepository<TaskRecord, UUID> {
    TaskRecord findTaskRecordByTaskId(UUID taskId);

}
