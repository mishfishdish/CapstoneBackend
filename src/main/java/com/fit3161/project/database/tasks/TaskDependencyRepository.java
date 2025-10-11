package com.fit3161.project.database.tasks;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskDependencyRepository extends CrudRepository<TaskDependencies, UUID> {
    void removeTaskDependenciesByTask(TaskRecord taskId);

    void removeTaskDependenciesByDependsOnEvent(EventRecord eventId);

    TaskDependencies findEventDependenciesByTask(TaskRecord taskId);

}
