package com.fit3161.project.endpoint.analytics.clubAnalytics;

import com.fit3161.project.database.Database;
import com.fit3161.project.endpoint.analytics.clubAnalytics.response.UserClubAnalytics;
import com.fit3161.project.managers.ClientManager;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class ClubAnalyticsService {

    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public List<UserClubAnalytics> getResponse() {
        // Get all clubs for the user
        var userClubs = database.getClubFromUser(client.getUserId());
        var analytics = new java.util.ArrayList<UserClubAnalytics>();

        for (var club : userClubs) {
            var attendanceThisMonth = database.getAttendanceThisMonth(
                club.getClubId()
            );
            var monthlyAverage = database.getMonthlyAverageAttendance(
                club.getClubId()
            );
            var taskStats = database
                .getTaskRecordRepository()
                .getTaskStatsByUserAndClub(
                    client.getUserId(),
                    club.getClubId()
                );
            UserClubAnalytics.TaskSummary taskSummary;
            if (taskStats.isEmpty()) {
                taskSummary = UserClubAnalytics.TaskSummary.builder()
                    .completed(0)
                    .overdue(0)
                    .ongoing(0)
                    .build();
            } else {
                var stats = taskStats.get(0);
                taskSummary = UserClubAnalytics.TaskSummary.builder()
                    .completed(((Number) stats[0]).intValue())
                    .overdue(((Number) stats[1]).intValue())
                    .ongoing(((Number) stats[2]).intValue())
                    .build();
            }

            var attendanceData = database
                .getAttendanceRepository()
                .getAttendanceByClubAndMonth(club.getClubId());
            var monthlyAttendance = new java.util.ArrayList<
                UserClubAnalytics.MonthlyAttendance
            >();
            for (Object[] row : attendanceData) {
                int month = ((Number) row[0]).intValue();
                int year = ((Number) row[1]).intValue();
                int count = ((Number) row[2]).intValue();
                String monthYear = String.format("%02d/%d", month, year);
                monthlyAttendance.add(
                    UserClubAnalytics.MonthlyAttendance.builder()
                        .month(monthYear)
                        .value(count)
                        .build()
                );
            }

            analytics.add(
                UserClubAnalytics.builder()
                    .clubId(club.getClubId())
                    .clubName(club.getClubName())
                    .attendanceThisMonth(attendanceThisMonth)
                    .monthlyAverage(monthlyAverage)
                    .tasks(taskSummary)
                    .attendanceLast12Months(monthlyAttendance)
                    .build()
            );
        }

        return analytics;
    }
}
