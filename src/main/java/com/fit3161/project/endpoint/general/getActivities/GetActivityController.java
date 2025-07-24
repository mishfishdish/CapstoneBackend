package com.fit3161.project.endpoint.general.getActivities;

import com.fit3161.project.managers.ClientManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GetActivityController {

    private final GetActivityService getActivityService;
    private final ClientManager client;

    @GetMapping("api/clubs/activity")
    public ResponseEntity<Object> handler(
            @RequestParam(defaultValue = "date") String sort,
            @RequestParam(required = false) UUID clubId,
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size

    ) {
        client.setClubId(clubId);
        client.setUserId(userId);
        client.setSearch(search);
        client.setPage(page);
        client.setSize(size);
        client.setSort(sort);

        return new ResponseEntity<>(getActivityService.getResponse(), getActivityService.getStatus());
    }
}