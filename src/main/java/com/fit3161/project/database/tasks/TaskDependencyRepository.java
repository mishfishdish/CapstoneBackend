package com.fit3161.project.database.tasks;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskDependencyRepository extends CrudRepository<TaskDependencies, UUID> {
    void removeTaskDependenciesByTask(TaskRecord taskId);

    TaskDependencies findEventDependenciesByTask(TaskRecord taskId);

}
