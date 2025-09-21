package com.fit3161.project.endpoint.activityManagement.createEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CreateEventController {

    private final CreateEventService createEventService;
    private final ClientManager client;

    @PostMapping("/api/events")
    public ResponseEntity<String> handler(@Valid @RequestBody CreateEventRequest request) throws JsonProcessingException {
        client.setRequest(request);
        return new ResponseEntity<>(createEventService.createEvent(), createEventService.getStatus());
    }
}