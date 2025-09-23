package com.fit3161.project.endpoint.home;

import com.fit3161.project.endpoint.home.response.DashboardResponse;
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
public class HomeController {

    private final HomeService homeService;
    private final ClientManager client;

    @GetMapping("/api/homeview/{userId}")
    public ResponseEntity<DashboardResponse> handler(@Valid @PathVariable UUID userId) {
        client.setUserId(userId);
        return new ResponseEntity<>(homeService.getResponse(), homeService.getStatus());
    }
}