package com.fit3161.project.endpoint.activityManagement.common;

import lombok.Data;

import java.util.UUID;

@Data
public class Notification {

    private UUID userId;
    private Integer notifyBeforeMinutes;
}