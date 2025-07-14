package com.fit3161.project.endpoint.onboarding.CreateUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fit3161.project.endpoint.onboarding.CreateUser.request.UserRequest;
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
public class CreateUserController {

    private final CreateUserService userService;
    private final ClientManager client;

    @PostMapping("/api/auth")
    public ResponseEntity<String> handler(@Valid @RequestBody UserRequest request) throws JsonProcessingException {
        client.setRequest(request);
        return new ResponseEntity<>(userService.getResponse(),userService.getStatus());
    }
}