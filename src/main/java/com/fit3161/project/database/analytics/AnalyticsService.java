package com.fit3161.project.database.analytics;

import com.fit3161.project.database.attendance.AttendanceRepository;
import com.fit3161.project.database.tasks.TaskRecordRepository;
import java.util.List;
import java.util.UUID;

public interface AnalyticsService {
    AttendanceRepository getAttendanceRepository();
    TaskRecordRepository getTaskRecordRepository();

    default int getAttendanceThisMonth(UUID clubId) {
        Integer count = getAttendanceRepository().countAttendanceThisMonth(
            clubId
        );
        return count != null ? count : 0;
    }

    default Integer getMonthlyAverageAttendance(UUID clubId) {
        Double avg = getAttendanceRepository().getMonthlyAverageAttendance(
            clubId
        );
        return avg != null ? avg.intValue() : null;
    }

    default List<Object[]> getAttendanceByClubAndMonth(UUID clubId) {
        return getAttendanceRepository().getAttendanceByClubAndMonth(clubId);
    }

    default List<Object[]> getTaskStatsByUserAndClub(UUID userId, UUID clubId) {
        return getTaskRecordRepository().getTaskStatsByUserAndClub(
            userId,
            clubId
        );
    }
}
