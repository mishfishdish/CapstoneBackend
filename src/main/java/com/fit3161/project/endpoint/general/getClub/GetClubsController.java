package com.fit3161.project.endpoint.general.getClub;

import com.fit3161.project.database.club.ClubResponse;
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

    @GetMapping("api/users/{userId}/clubs")
    public ResponseEntity<List<ClubResponse>> handler(@Valid @PathVariable UUID userId ){
        client.setUserId(userId);
        return new ResponseEntity<>(getClubsService.getResponse(),getClubsService.getStatus());
    }
}