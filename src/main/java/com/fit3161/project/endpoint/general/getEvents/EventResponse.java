package com.fit3161.project.endpoint.general.getEvents;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@Data
public class EventResponse {
    UUID activityId;
    String activityTitle;
    LocalDateTime startTime;
    LocalDateTime endTime;
    UUID dependsOnEventId;
}
