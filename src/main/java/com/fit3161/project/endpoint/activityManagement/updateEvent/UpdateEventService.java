package com.fit3161.project.endpoint.activityManagement.updateEvent;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.notification.NotificationRecord;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class UpdateEventService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        final CreateEventRequest request = client.getRequestAs(CreateEventRequest.class);

        // 1. Fetch the existing event
        EventRecord existing = database.findEvent(client.getEventId());
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // 2. Update basic fields only if provided
        if (request.getTitle() != null) existing.setTitle(request.getTitle());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getStartTime() != null) existing.setStartTime(request.getStartTime());
        if (request.getEndTime() != null) existing.setEndTime(request.getEndTime());
        if (request.getLocation() != null) existing.setLocation(request.getLocation());

        database.saveEventRecord(existing);

        if (request.getClubIds() != null) {
            database.removeEvent(existing); // remove all old links
                for (UUID clubId : request.getClubIds()) {
                    database.addEventToClub(eventclub ->
                            eventclub.event(existing).club(database.findClub(clubId)));
                }
            }

            // 4. Update parent event
            database.removeEventDependencies(existing); // remove existing parent link
            if (request.getParentEventId() != null) {
                database.addEventToDependOnEvent(dependency ->
                        dependency.eventId(existing).dependEventId(database.findEvent(request.getParentEventId())));
            }

            // 5. Update or create notification
            if (request.getNotification() != null) {
                NotificationRecord existingNotification = database.findNotification(existing);
                if (existingNotification != null) {
                    existingNotification.setNotifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes());
                    existingNotification.setUserId(database.findUser(String.valueOf(request.getUserId())));
                    database.updateNotification(existingNotification);
                } else {
                    database.createNotification(notification ->
                            notification.event(existing)
                                    .notifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes())
                                    .userId(database.findUser(String.valueOf(request.getUserId())))
                    );
                }
            } else {
                NotificationRecord existingNotification = database.findNotification(existing);
                database.deleteNotification(existingNotification);
            }

            return null;
        }

    }
