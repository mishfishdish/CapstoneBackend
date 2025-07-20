package com.fit3161.project.endpoint.general.getEventDetails;

import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EventDetailsController {

    private final EventDetailsService eventDetailsService;
    private final ClientManager client;

    @GetMapping("/api/events/{eventId}")
    public ResponseEntity<EventDetailsResponse> handler(@Valid @PathVariable UUID eventId) {
        client.setEventId(eventId);
        return new ResponseEntity<>(eventDetailsService.getResponse(), eventDetailsService.getStatus());
    }
}