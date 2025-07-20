package com.fit3161.project.endpoint.general.getEvents;

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
public class GetEventController {

    private final GetEventService getEventService;
    private final ClientManager client;

    @GetMapping("api/clubs/{clubId}/events")
    public ResponseEntity<List<EventResponse>> handler(@Valid @PathVariable UUID clubId){
        client.setClubId(clubId);
        return new ResponseEntity<>(getEventService.getResponse(),getEventService.getStatus());
    }
}