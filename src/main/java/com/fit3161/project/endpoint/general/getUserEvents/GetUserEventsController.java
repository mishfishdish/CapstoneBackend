package com.fit3161.project.endpoint.general.getUserEvents;

import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GetUserEventsController {

    private final GetUserEventsService getUserEventsService;
    private final ClientManager client;

    @GetMapping("/api/users/{userId}/events")
    public ResponseEntity<List<UserEventSummary>> handler(@Valid @PathVariable UUID userId) {
        client.setUserId(userId);
        return new ResponseEntity<>(getUserEventsService.getResponse(), getUserEventsService.getStatus());
    }
}
