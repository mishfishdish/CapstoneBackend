package com.fit3161.project.endpoint.onboarding.CreateClub;

import com.fit3161.project.endpoint.onboarding.CreateClub.request.CreateClubRequest;
import com.fit3161.project.endpoint.onboarding.CreateClub.response.CreateResponse;
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
public class CreateClubController {

    private final CreateClubService clubService;
    private final ClientManager client;

    @PostMapping("/api/clubs")
    public ResponseEntity<CreateResponse> handler(@Valid @RequestBody CreateClubRequest request) {
        client.setRequest(request);
        return new ResponseEntity<>(clubService.getResponse(),clubService.getStatus());
    }
}