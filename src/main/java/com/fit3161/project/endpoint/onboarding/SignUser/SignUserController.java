package com.fit3161.project.endpoint.onboarding.SignUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fit3161.project.endpoint.onboarding.SignUser.request.SignRequest;
import com.fit3161.project.endpoint.onboarding.SignUser.response.SignResponse;
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
public class SignUserController {

    private final SignUserService userService;
    private final ClientManager client;

    @PostMapping("/api/auth/login")
    public ResponseEntity<SignResponse> handler(@Valid @RequestBody SignRequest request) throws JsonProcessingException {
        client.setRequest(request);
        return new ResponseEntity<>(userService.getResponse(),userService.getStatus());
    }
}