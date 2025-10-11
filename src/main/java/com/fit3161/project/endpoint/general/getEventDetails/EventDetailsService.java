package com.fit3161.project.endpoint.general.getEventDetails;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.notification.NotificationRecord;
import com.fit3161.project.managers.ClientManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Getter
@Transactional
@RequiredArgsConstructor
public class EventDetailsService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public EventDetailsResponse getResponse() {

        EventDetailsResponse.EventDetailsResponseBuilder response = new EventDetailsResponse.EventDetailsResponseBuilder();

        // 1. Fetch the existing event
        EventRecord existing = database.findEvent(client.getEventId());

        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        response.title(existing.getTitle());
        response.description(existing.getDescription());
        response.startTime(existing.getStartTime());
        response.endTime(existing.getEndTime());
        response.location(existing.getLocation());
        response.clubs(database.findEventClubIds(existing).toList());
        NotificationRecord notification = database.findNotification(existing);
        if (notification != null) {
            response.notifyBeforeMinutes(notification.getNotifyBeforeMinutes());
        }

        UUID parentEventId = database.findEventDependency(existing);
        if (parentEventId != null) {
            response.parentEventId(parentEventId);
        }
        response.qrCode(database.findQr(existing).getQrCode());


        return response.build();
    }

}
