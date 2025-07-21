package com.fit3161.project.endpoint.activityManagement.updateTask.request;

import com.fit3161.project.endpoint.activityManagement.common.Notification;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class UpdateTaskRequest {

    private String title;
    private LocalDateTime dueDate;
    private String description;
    private List<UUID> clubIds;
    private String priority;
    private UUID parentEventId;
    private Notification notification;
    private Boolean completed;
    private UUID userId;
}