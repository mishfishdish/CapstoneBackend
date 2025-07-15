package com.fit3161.project.endpoint.activityManagement.getClub;

import com.fit3161.project.database.club.ClubResponse;
import com.fit3161.project.endpoint.onboarding.AddToClub.AddToClubService;
import com.fit3161.project.endpoint.onboarding.AddToClub.request.AddClubRequest;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GetClubsController {

    private final GetClubsService getClubsService;
    private final ClientManager client;

    @GetMapping("/users/{userId}/clubs")
    public ResponseEntity<List<ClubResponse>> handler(@Valid @PathVariable UUID userId ){
        client.setUserId(userId);
        return new ResponseEntity<>(getClubsService.getResponse(),getClubsService.getStatus());
    }
}