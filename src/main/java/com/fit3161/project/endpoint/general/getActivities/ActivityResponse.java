package com.fit3161.project.endpoint.general.getActivities;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class ActivityResponse {
    String activityId;
    String activityTitle;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String type;
    UUID dependsOnEventId;

    public ActivityResponse(String activityId, String activityTitle,
                            Timestamp startTime, Timestamp endTime,
                            String type, UUID dependsOnEventId) {
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.startTime = startTime != null ? startTime.toLocalDateTime() : null;
        this.endTime = endTime != null ? endTime.toLocalDateTime() : null;
        this.type = type;
        this.dependsOnEventId = dependsOnEventId;
    }
}