package com.fit3161.project.endpoint.general.getTaskDetails;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TaskDetailsResponse {

    private String title;
    private LocalDateTime dueDate;
    private String description;
    private List<UUID> clubs;
    private String priority;
    private UUID parentEventId;
    private Integer notifyBeforeMinutes;
}