package com.fit3161.project.endpoint.general.getActivities;

import java.time.LocalDateTime;
import java.util.UUID;


public interface ActivityResponse {
    String getActivityId();

    String getActivityTitle();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    String getType();

    UUID getDependsOnEventId();
}