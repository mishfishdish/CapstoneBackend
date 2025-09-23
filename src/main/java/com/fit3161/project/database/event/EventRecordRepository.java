package com.fit3161.project.database.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EventRecordRepository extends CrudRepository<EventRecord, UUID> {
    EventRecord findEventRecordByEventId(UUID eventId);

    @Query(value = """
            SELECT
                COUNT(*) FILTER (WHERE e.end_time < CURRENT_TIMESTAMP) AS past_event_count,
                COUNT(*) AS total_event_count
            FROM events e
            JOIN event_clubs ec ON e.event_id = ec.event_id
            JOIN user_clubs uc ON ec.club_id = uc.club_id
            WHERE uc.user_id = :userId
            """, nativeQuery = true)
    List<Object[]> findEventCountsForUser(@Param("userId") UUID userId);
}
