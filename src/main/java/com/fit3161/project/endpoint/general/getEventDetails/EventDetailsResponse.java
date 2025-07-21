package com.fit3161.project.endpoint.general.getEventDetails;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class EventDetailsResponse {

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String description;
    private List<UUID> clubs;
    private UUID parentEventId;
    private Integer notifyBeforeMinutes;
    private Boolean completed;
    private String qrCode;
}