package com.fit3161.project.endpoint.analytics.clubAnalytics.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserClubAnalytics {
    private UUID clubId;
    private String clubName;
    private int attendanceThisMonth;
    private Integer monthlyAverage;
    private TaskSummary tasks;
    private List<MonthlyAttendance> attendanceLast12Months;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TaskSummary {
        private int completed;
        private int overdue;
        private int ongoing;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MonthlyAttendance {
        private String month;
        private int value;
    }
}
