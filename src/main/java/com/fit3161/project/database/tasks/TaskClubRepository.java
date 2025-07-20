package com.fit3161.project.database.tasks;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskClubRepository extends CrudRepository<TaskClubs, UUID> {
    void removeTaskClubsByEvent(TaskRecord taskRecord);

    List<TaskClubs> findTaskClubsByTask(TaskRecord taskRecord);

}
