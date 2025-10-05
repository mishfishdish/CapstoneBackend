package com.fit3161.project.database.attendance;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AttendanceRepository
    extends CrudRepository<AttendanceRecord, UUID> {
    @Query(
        value = """
        SELECT COUNT(DISTINCT a.attendance_id)
        FROM attendance a
        JOIN events e ON a.event_id = e.event_id
        JOIN event_clubs ec ON e.event_id = ec.event_id
        WHERE ec.club_id = :clubId
          AND EXTRACT(MONTH FROM a.timestamp) = EXTRACT(MONTH FROM CURRENT_DATE)
          AND EXTRACT(YEAR FROM a.timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)
        """,
        nativeQuery = true
    )
    Integer countAttendanceThisMonth(@Param("clubId") UUID clubId);

    @Query(
        value = """
        SELECT
            EXTRACT(MONTH FROM a.timestamp) as month,
            EXTRACT(YEAR FROM a.timestamp) as year,
            COUNT(DISTINCT a.attendance_id) as attendance_count
        FROM attendance a
        JOIN events e ON a.event_id = e.event_id
        JOIN event_clubs ec ON e.event_id = ec.event_id
        WHERE ec.club_id = :clubId
          AND a.timestamp >= CURRENT_DATE - INTERVAL '12 months'
        GROUP BY EXTRACT(YEAR FROM a.timestamp), EXTRACT(MONTH FROM a.timestamp)
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
                EXTRACT(MONTH FROM a.timestamp) as month,
                EXTRACT(YEAR FROM a.timestamp) as year,
                COUNT(DISTINCT a.attendance_id) as attendance_count
            FROM attendance a
            JOIN events e ON a.event_id = e.event_id
            JOIN event_clubs ec ON e.event_id = ec.event_id
            WHERE ec.club_id = :clubId
              AND a.timestamp >= CURRENT_DATE - INTERVAL '12 months'
            GROUP BY EXTRACT(YEAR FROM a.timestamp), EXTRACT(MONTH FROM a.timestamp)
        ) monthly_counts
        """,
        nativeQuery = true
    )
    Double getMonthlyAverageAttendance(@Param("clubId") UUID clubId);
}
