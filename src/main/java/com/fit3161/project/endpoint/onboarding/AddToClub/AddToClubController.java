package com.fit3161.project.endpoint.onboarding.AddToClub;

import com.fit3161.project.endpoint.onboarding.AddToClub.request.AddClubRequest;
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
public class AddToClubController {

    private final AddToClubService addToClubService;
    private final ClientManager client;

    @PostMapping("/api/clubs/user")
    public ResponseEntity<String> handler(@Valid @RequestBody AddClubRequest request) {
        client.setRequest(request);
        return new ResponseEntity<>(addToClubService.getResponse(),addToClubService.getStatus());
    }
}