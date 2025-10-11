package com.fit3161.project.database.attendance;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AttendanceRepository
        extends CrudRepository<AttendanceRecord, UUID> {
    @Query(
            value = """
                    SELECT COUNT(DISTINCT a.attendance_id)
                    FROM attendance a
                    JOIN events e ON a.event_id = e.event_id
                    JOIN event_clubs ec ON e.event_id = ec.event_id
                    WHERE ec.club_id = :clubId
                      AND EXTRACT(MONTH FROM e.end_time) = EXTRACT(MONTH FROM CURRENT_DATE)
                      AND EXTRACT(YEAR FROM e.end_time) = EXTRACT(YEAR FROM CURRENT_DATE)
                    """,
            nativeQuery = true
    )
    Integer countAttendanceThisMonth(@Param("clubId") UUID clubId);

    @Query(
            value = """
                    SELECT
                        EXTRACT(MONTH FROM e.end_time) as month,
                        EXTRACT(YEAR FROM e.end_time) as year,
                        COUNT(DISTINCT a.attendance_id) as attendance_count
                    FROM attendance a
                    JOIN events e ON a.event_id = e.event_id
                    JOIN event_clubs ec ON e.event_id = ec.event_id
                    WHERE ec.club_id = :clubId
                      AND e.end_time >= CURRENT_DATE - INTERVAL '12 months'
                    GROUP BY EXTRACT(YEAR FROM e.end_time), EXTRACT(MONTH FROM e.end_time)
                    ORDER BY year DESC, month DESC
                    """,
            nativeQuery = true
    )
    List<Object[]> getAttendanceByClubAndMonth(@Param("clubId") UUID clubId);

    @Query(
            value = """
                    SELECT COALESCE(AVG(monthly_counts.attendance_count), 0)
                    FROM (
                        SELECT
                            EXTRACT(MONTH FROM e.end_time) as month,
                            EXTRACT(YEAR FROM e.end_time) as year,
                            COUNT(DISTINCT a.attendance_id) as attendance_count
                        FROM attendance a
                        JOIN events e ON a.event_id = e.event_id
                        JOIN event_clubs ec ON e.event_id = ec.event_id
                        WHERE ec.club_id = :clubId
                          AND e.end_time >= CURRENT_DATE - INTERVAL '12 months'
                        GROUP BY EXTRACT(YEAR FROM e.end_time), EXTRACT(MONTH FROM e.end_time)
                    ) monthly_counts
                    """,
            nativeQuery = true
    )
    Double getMonthlyAverageAttendance(@Param("clubId") UUID clubId);

    List<AttendanceRecord> findAttendanceRecordsByEvent(EventRecord eventRecord);

    void removeAttendanceRecordsByEvent(EventRecord eventRecord);

}
