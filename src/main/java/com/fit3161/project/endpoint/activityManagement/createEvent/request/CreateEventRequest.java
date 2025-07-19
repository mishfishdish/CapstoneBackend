package com.fit3161.project.endpoint.activityManagement.createEvent.request;

import com.fit3161.project.endpoint.activityManagement.common.Notification;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class CreateEventRequest {

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String description;
    private List<UUID> clubIds;
    private UUID parentEventId;
    private Notification notification;
    private UUID userId;
}