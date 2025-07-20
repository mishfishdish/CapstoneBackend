package com.fit3161.project.endpoint.general.getActivities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@Data
public class ActivityResponse {
    UUID activityId;
    String activityTitle;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String type;
    UUID dependsOnEventId;
}
