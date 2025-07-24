package com.fit3161.project.endpoint.activityManagement.createTask.request;

import com.fit3161.project.endpoint.activityManagement.common.Notification;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CreateTaskRequest {

    private String title;
    private LocalDateTime dueDate;
    private String description;
    private List<UUID> clubs;
    private String priority;
    private UUID parentEvent;
    private Notification notification;
    private UUID userId;
}