package com.fit3161.project.database.tasks;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskClubRepository extends CrudRepository<TaskClubs, UUID> {
    void removeTaskClubsByEvent(TaskRecord taskRecord);

}
