package com.fit3161.project.endpoint.general.getActivities;

import com.fit3161.project.database.Database;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class GetActivityService {
    private final Database database;
    private final ClientManager client;
    private String search;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public Object getResponse() {
        if (client.getPage().isEmpty() || client.getSize().isEmpty()) {
            return database.getAllActivities(client.getClubId());
        }

        Pageable pageable = PageRequest.of(
                client.getPage().get(),
                client.getSize().get(),
                client.getSort().equalsIgnoreCase("startTime") ? Sort.by("startTime") : Sort.by("activityTitle")
        );

        if (client.getSearch() != null && !client.getSearch().isBlank()) {
            search = "%" + client.getSearch().trim().toLowerCase() + "%";
        } else {
            search = "%";
        }

        return database.findAllByUserIdAndSearch(client.getClubId(), search, pageable);
    }
}
