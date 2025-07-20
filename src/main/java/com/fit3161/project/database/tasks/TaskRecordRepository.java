package com.fit3161.project.database.tasks;

import com.fit3161.project.endpoint.general.getActivities.ActivityResponse;
import com.fit3161.project.endpoint.general.getEvents.EventResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRecordRepository extends CrudRepository<TaskRecord, UUID> {
    TaskRecord findTaskRecordByTaskId(UUID taskId);

    @Query(value = """
                        (
                SELECT\s
                    t.task_id AS activityId,
                    t.title AS activityTitle,
                    NULL AS startTime,
                    t.deadline AS endTime,
                    td.depends_on_event_id AS dependsOnEventId,
                    'task' AS type
                FROM tasks t
                JOIN task_clubs tc ON t.task_id = tc.task_id
                LEFT JOIN task_dependencies td ON td.task_id = t.task_id AND td.club_id = :clubId
                WHERE tc.club_id = :clubId
            )
            UNION
            (
                SELECT\s
                    e.event_id AS activityId,
                    e.title AS activityTitle,
                    e.start_time AS startTime,
                    e.end_time AS endTime,
                    NULL AS dependsOnEventId,
                    'event' AS type
                FROM events e
                JOIN event_clubs ec ON e.event_id = ec.event_id
                WHERE ec.club_id = :clubId
            ) 
            """, nativeQuery = true)
    List<ActivityResponse> findAllActivitiesByClubId(@Param("clubId") UUID clubId);

    @Query(value = """
            (
                SELECT 
                    e.event_id AS activityId,
                    e.title AS activityTitle,
                    e.start_time AS startTime,
                    e.end_time AS endTime,
                    NULL AS dependsOnEventId
                FROM events e
                JOIN event_clubs ec ON e.event_id = ec.event_id
                WHERE ec.club_id = :clubId
            )
            """, nativeQuery = true)
    List<EventResponse> findAllEventsByClubId(@Param("clubId") UUID clubId);
}
