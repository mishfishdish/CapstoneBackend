package com.fit3161.project.endpoint.onboarding.InviteClub;


import com.fit3161.project.endpoint.onboarding.CreateUser.CreateUserService;
import com.fit3161.project.endpoint.onboarding.CreateUser.request.UserRequest;
import com.fit3161.project.endpoint.onboarding.InviteClub.request.InviteRequest;
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
public class InviteController {

    private final InviteService inviteService;
    private final ClientManager client;

    @PostMapping("/api/clubs/invite")
    public ResponseEntity<String> handler(@Valid @RequestBody InviteRequest request) {
        client.setRequest(request);
        return new ResponseEntity<>(inviteService.getResponse(),inviteService.getStatus());
    }
}