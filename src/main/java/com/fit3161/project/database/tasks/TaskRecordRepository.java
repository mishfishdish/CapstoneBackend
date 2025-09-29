package com.fit3161.project.database.tasks;

import com.fit3161.project.endpoint.general.getActivities.ActivityResponse;
import com.fit3161.project.endpoint.general.getEvents.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRecordRepository extends CrudRepository<TaskRecord, UUID> {
    TaskRecord findTaskRecordByTaskId(UUID taskId);

    @Query(value = """
                        (
                SELECT
                    t.task_id AS activityId,
                    t.title AS activityTitle,
                    NULL AS startTime,
                    t.deadline AS endTime,
                    td.depends_on_event_id AS dependsOnEventId,
                    'task' AS type
                FROM tasks t
                JOIN task_clubs tc ON t.task_id = tc.task_id
                JOIN user_clubs uc ON tc.club_id = uc.club_id
                LEFT JOIN task_dependencies td ON td.task_id = t.task_id
            )
            UNION
            (
                SELECT
                    e.event_id AS activityId,
                    e.title AS activityTitle,
                    e.start_time AS startTime,
                    e.end_time AS endTime,
                    ed.depends_on_event_id AS dependsOnEventId,
                    'event' AS type
                FROM events e
                JOIN event_clubs ec ON e.event_id = ec.event_id
                JOIN user_clubs uc ON ec.club_id = uc.club_id
                LEFT JOIN event_dependencies ed ON ed.event_id = e.event_id 
            ) 
            """, nativeQuery = true)
    List<ActivityResponse> findAllActivitiesByClubId(@Param("clubId") UUID clubId);

    @Query(value = """
            (
                SELECT
                    t.task_id AS activityId,
                    t.title AS activityTitle,
                    NULL AS startTime,
                    t.deadline AS endTime,
                    td.depends_on_event_id AS dependsOnEventId,
                    'task' AS type
                FROM tasks t
                JOIN task_clubs tc ON t.task_id = tc.task_id
                JOIN user_clubs uc ON tc.club_id = uc.club_id
                LEFT JOIN task_dependencies td ON td.task_id = t.task_id AND td.club_id = uc.club_id
                WHERE uc.user_id = :userId
            )
            UNION
            (
                SELECT
                    e.event_id AS activityId,
                    e.title AS activityTitle,
                    e.start_time AS startTime,
                    e.end_time AS endTime,
                    ed.depends_on_event_id AS dependsOnEventId,
                    'event' AS type
                FROM events e
                JOIN event_clubs ec ON e.event_id = ec.event_id
                JOIN user_clubs uc ON ec.club_id = uc.club_id
                LEFT JOIN event_dependencies td ON ed.event_id = e.event_id AND ed.club_id = ec.club_id
                WHERE uc.user_id = :userId
            )
            """, nativeQuery = true)
    List<ActivityResponse> findAllActivitiesByUserId(@Param("userId") UUID userId);

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

    @Query(value = """
            SELECT
                COUNT(*) FILTER (
                    WHERE t.completed = TRUE
                      AND t.deadline < CURRENT_TIMESTAMP
                ) AS completed_past_count,
            
                COUNT(*) FILTER (
                    WHERE t.completed = FALSE
                      AND t.deadline < CURRENT_TIMESTAMP
                ) AS not_completed_past_count,
            
                COUNT(*) FILTER (
                    WHERE t.deadline >= CURRENT_TIMESTAMP
                ) AS not_past_count
            
            FROM tasks t
            JOIN task_clubs tc ON t.task_id = tc.task_id
            JOIN user_clubs uc ON tc.club_id = uc.club_id
            WHERE uc.user_id = CAST(:userId AS uuid)
            """, nativeQuery = true)
    List<Object[]> countCompletedAndTotalTasksForUserClubs(@Param("userId") UUID userId);

    @Query(value = """
            SELECT title, datetime, type
            FROM (
                SELECT
                    t.title AS title,
                    t.deadline AS datetime,
                    'task' AS type
                FROM tasks t
                JOIN task_clubs tc ON t.task_id = tc.task_id
                JOIN user_clubs uc ON tc.club_id = uc.club_id
                WHERE uc.user_id = CAST(:userId AS uuid)
                  AND t.deadline > CURRENT_TIMESTAMP
            
                UNION ALL
            
                SELECT
                    e.title AS title,
                    e.start_time AS datetime,
                    'event' AS type
                FROM events e
                JOIN event_clubs ec ON e.event_id = ec.event_id
                JOIN user_clubs uc ON ec.club_id = uc.club_id
                WHERE uc.user_id = CAST(:userId AS uuid)
                  AND e.start_time > CURRENT_TIMESTAMP
            ) combined
            ORDER BY datetime ASC
            LIMIT 5;""", nativeQuery = true)
    List<Object[]> findTop5UpcomingTasksAndEvents(@Param("userId") UUID userId);

    @Query(value = """
            (
                SELECT
                    CAST(t.task_id AS VARCHAR)      AS activityId,
                    t.title                         AS activityTitle,
                    CAST(NULL AS TIMESTAMP)         AS startTime,
                    t.deadline                      AS endTime,
                    'task'                          AS type,
                    td.depends_on_event_id          AS dependsOnEventId
                FROM tasks t
                JOIN task_clubs tc ON t.task_id = tc.task_id
                LEFT JOIN task_dependencies td ON td.task_id = t.task_id
                WHERE (:clubId IS NULL OR tc.club_id = CAST(:clubId AS UUID))
                  AND LOWER(t.title) LIKE :search
            )
            UNION
            (
                SELECT
                    CAST(e.event_id AS VARCHAR)     AS activityId,
                    e.title                         AS activityTitle,
                    e.start_time                    AS startTime,
                    e.end_time                      AS endTime,
                    'event'                         AS type,
                    ed.depends_on_event_id          AS dependsOnEventId
                FROM events e
                JOIN event_clubs ec ON e.event_id = ec.event_id
                LEFT JOIN event_dependencies ed ON ed.event_id = e.event_id
                WHERE (:clubId IS NULL OR ec.club_id = CAST(:clubId AS UUID))
                  AND LOWER(e.title) LIKE :search
            )
            """,
            countQuery = """
                    SELECT COUNT(*) FROM (
                        (SELECT 1
                         FROM tasks t
                         JOIN task_clubs tc ON t.task_id = tc.task_id
                         WHERE (:clubId IS NULL OR tc.club_id = CAST(:clubId AS UUID))
                           AND LOWER(t.title) LIKE :search)
                        UNION
                        (SELECT 1
                         FROM events e
                         JOIN event_clubs ec ON e.event_id = ec.event_id
                         WHERE (:clubId IS NULL OR ec.club_id = CAST(:clubId AS UUID))
                           AND LOWER(e.title) LIKE :search)
                    ) as combined
                    """,
            nativeQuery = true)
    Page<ActivityResponse> findAllByUserIdAndSearch(
            @Param("clubId") UUID clubId,
            @Param("search") String search,
            Pageable pageable
    );


}
