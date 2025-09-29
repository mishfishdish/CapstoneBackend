package com.fit3161.project.endpoint.general.getEvents;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EventResponse {
    private UUID activityId;
    private String activityTitle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID dependsOnEventId;

    // Hibernate will call this constructor from native query
    public EventResponse(UUID activityId,
                         String activityTitle,
                         Timestamp startTime,
                         Timestamp endTime,
                         UUID dependsOnEventId) {
        this.activityId = activityId;
        this.activityTitle = activityTitle;
        this.startTime = startTime != null ? startTime.toLocalDateTime() : null;
        this.endTime = endTime != null ? endTime.toLocalDateTime() : null;
        this.dependsOnEventId = dependsOnEventId;
    }
}
