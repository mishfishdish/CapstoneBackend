package com.fit3161.project.endpoint.activityManagement.updateEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.endpoint.activityManagement.updateEvent.request.UpdateEventRequest;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UpdateEventController {

    private final UpdateEventService updateEventService;
    private final ClientManager client;

    @PutMapping("/api/events/{eventId}")
    public ResponseEntity<String> handler(@Valid @PathVariable UUID eventId, @Valid @RequestBody UpdateEventRequest request) {
        client.setRequest(request);
        client.setEventId(eventId);
        return new ResponseEntity<>(updateEventService.getResponse(),updateEventService.getStatus());
    }
}