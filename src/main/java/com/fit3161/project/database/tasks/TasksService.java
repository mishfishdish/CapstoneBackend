package com.fit3161.project.database.tasks;

import com.fit3161.project.database.event.*;

import java.util.UUID;
import java.util.function.Consumer;

public interface TasksService {
    TaskClubRepository getTaskClubRepository();
    TaskDependencyRepository getTaskDependencyRepository();
    TaskRecordRepository getTaskRecordRepository();

    default TaskRecord createTask(Consumer<TaskRecord.TaskRecordBuilder> consumer){
        final TaskRecord.TaskRecordBuilder taskRecordBuilder = new TaskRecord.TaskRecordBuilder();
        consumer.accept(taskRecordBuilder);
        return getTaskRecordRepository().save(taskRecordBuilder.build());
    }

    default TaskRecord saveTaskRecord(final TaskRecord task){
        return getTaskRecordRepository().save(task);
    }

    default TaskClubs addTaskToClub(final Consumer<TaskClubs.TaskClubsBuilder> consumer){
        final TaskClubs.TaskClubsBuilder task = new TaskClubs.TaskClubsBuilder();
        consumer.accept(task);
        return getTaskClubRepository().save(task.build());
    }

    default TaskDependencies addTaskToDependOnEvent(final Consumer<TaskDependencies.TaskDependenciesBuilder> consumer){
        final TaskDependencies.TaskDependenciesBuilder dependency = new TaskDependencies.TaskDependenciesBuilder();
        consumer.accept(dependency);
        return getTaskDependencyRepository().save(dependency.build());
    }

    default TaskRecord findTask(UUID taskId){
        return getTaskRecordRepository().findTaskRecordByTaskId(taskId);
    }

    default void removeTaskClub(TaskRecord taskRecord){
        getTaskClubRepository().removeTaskClubsByEvent(taskRecord);
    }

    default void removeTaskDependencies(TaskRecord taskRecord){
        getTaskDependencyRepository().removeTaskDependenciesByTaskId(taskRecord);
    }
    default void removeTask(TaskRecord taskRecord){
        getTaskRecordRepository().delete(taskRecord);
    }

}
