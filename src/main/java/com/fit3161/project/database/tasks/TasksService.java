package com.fit3161.project.database.tasks;

import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.endpoint.general.getActivities.ActivityResponse;
import com.fit3161.project.endpoint.general.getEvents.EventResponse;
import com.fit3161.project.endpoint.home.response.Activity;
import com.fit3161.project.endpoint.home.response.TaskStats;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;


public interface TasksService {
    TaskClubRepository getTaskClubRepository();

    TaskDependencyRepository getTaskDependencyRepository();

    TaskRecordRepository getTaskRecordRepository();

    default TaskRecord createTask(Consumer<TaskRecord.TaskRecordBuilder> consumer) {
        final TaskRecord.TaskRecordBuilder taskRecordBuilder = new TaskRecord.TaskRecordBuilder();
        consumer.accept(taskRecordBuilder);
        return getTaskRecordRepository().save(taskRecordBuilder.build());
    }

    default TaskRecord saveTaskRecord(final TaskRecord task) {
        return getTaskRecordRepository().save(task);
    }

    default TaskClubs addTaskToClub(final Consumer<TaskClubs.TaskClubsBuilder> consumer) {
        final TaskClubs.TaskClubsBuilder task = new TaskClubs.TaskClubsBuilder();
        consumer.accept(task);
        return getTaskClubRepository().save(task.build());
    }

    default TaskDependencies addTaskToDependOnEvent(final Consumer<TaskDependencies.TaskDependenciesBuilder> consumer) {
        final TaskDependencies.TaskDependenciesBuilder dependency = new TaskDependencies.TaskDependenciesBuilder();
        consumer.accept(dependency);
        return getTaskDependencyRepository().save(dependency.build());
    }

    default TaskRecord findTask(UUID taskId) {
        return getTaskRecordRepository().findTaskRecordByTaskId(taskId);
    }

    default void removeTaskClub(TaskRecord taskRecord) {
        getTaskClubRepository().removeTaskClubsByTask(taskRecord);
    }

    default void removeTaskDependencies(TaskRecord taskRecord) {
        getTaskDependencyRepository().removeTaskDependenciesByTask(taskRecord);
    }

    default void removeTaskDependants(EventRecord eventRecord) {
        getTaskDependencyRepository().removeTaskDependenciesByDependsOnEvent(eventRecord);
    }

    default void removeTask(TaskRecord taskRecord) {
        getTaskRecordRepository().delete(taskRecord);
    }

    default List<ActivityResponse> getAllActivities(UUID clubId) {
        return getTaskRecordRepository().findAllActivitiesByClubId(clubId);
    }

    default List<ActivityResponse> getAllActivitiesByUser(UUID userId) {
        return getTaskRecordRepository().findAllActivitiesByUserId(userId);
    }

    default List<EventResponse> getAllEvents(UUID clubId) {
        return getTaskRecordRepository().findAllEventsByClubId(clubId);
    }


    default TaskDependencies findTaskDependency(TaskRecord taskRecord) {
        return getTaskDependencyRepository().findEventDependenciesByTask(taskRecord);
    }

    default Stream<UUID> findEventClubIds(TaskRecord eventRecord) {
        return getTaskClubRepository().findTaskClubsByTask(eventRecord).stream().map(
                eventClub -> eventClub.getClub().getClubId());
    }

    default TaskStats findTasksStats(UUID userId) {
        List<Object[]> result = getTaskRecordRepository().countCompletedAndTotalTasksForUserClubs(userId);
        Object[] row = result.get(0);

        TaskStats stats = new TaskStats();
        stats.setCompleted(((Number) row[0]).intValue());
        stats.setOverdue(((Number) row[1]).intValue());
        stats.setNotCompleted(((Number) row[2]).intValue());
        return stats;

    }

    default List<Activity> findUpcoming5Activities(UUID userId) {
        List<Object[]> results = getTaskRecordRepository().findTop5UpcomingTasksAndEvents(userId);
        List<Activity> activities = results.stream().map(row -> {
            Activity activity = new Activity();
            activity.setTitle((String) row[0]);
            activity.setTime(((Timestamp) row[1]).toLocalDateTime());
            activity.setType((String) row[2]);
            return activity;
        }).toList();
        return activities;

    }

    default Page<ActivityResponse> findAllByUserIdAndSearch(UUID clubId, String search, Pageable pageable) {
        return getTaskRecordRepository().findAllByUserIdAndSearch(clubId, search, pageable);
    }


}
