package com.fit3161.project.endpoint.activityManagement.createEvent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateEventResponse {

    private UUID eventId;
}