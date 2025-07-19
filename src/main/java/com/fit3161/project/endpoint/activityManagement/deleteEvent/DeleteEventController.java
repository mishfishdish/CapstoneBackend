package com.fit3161.project.endpoint.activityManagement.deleteEvent;

import com.fit3161.project.endpoint.activityManagement.deleteEvent.request.UpdateEventRequest;
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
public class DeleteEventController {

    private final DeleteEventService deleteEventService;
    private final ClientManager client;

    @DeleteMapping("/api/events/{eventId}")
    public ResponseEntity<String> handler(@Valid @PathVariable UUID eventId) {
        client.setEventId(eventId);
        return new ResponseEntity<>(deleteEventService.getResponse(),deleteEventService.getStatus());
    }
}