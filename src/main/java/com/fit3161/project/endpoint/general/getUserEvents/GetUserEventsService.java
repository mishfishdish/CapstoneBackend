package com.fit3161.project.endpoint.general.getUserEvents;

import com.fit3161.project.database.Database;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GetUserEventsService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public List<UserEventSummary> getResponse() {
        return database.getEventsForUser(client.getUserId());
    }
}
