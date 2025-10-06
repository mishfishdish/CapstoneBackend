package com.fit3161.project.endpoint.analytics.clubAnalytics;

import com.fit3161.project.endpoint.analytics.clubAnalytics.response.UserClubAnalytics;
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
public class ClubAnalyticsController {

    private final ClubAnalyticsService clubAnalyticsService;
    private final ClientManager client;

    @GetMapping("/api/clubs/analytic/{userId}")
    public ResponseEntity<List<UserClubAnalytics>> handler(@Valid @PathVariable UUID userId) {
        client.setUserId(userId);
        return new ResponseEntity<>(clubAnalyticsService.getResponse(), clubAnalyticsService.getStatus());
    }
}
