package com.fit3161.project.endpoint.general.getUserEvents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEventSummary {
    private UUID eventId;
    private String title;
}
