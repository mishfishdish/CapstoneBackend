package com.fit3161.project.endpoint.activityManagement.createEvent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.endpoint.onboarding.CreateUser.request.UserRequest;
import com.fit3161.project.endpoint.onboarding.CreateUser.response.CreateResponse;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CreateEventService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        final CreateEventRequest request = client.getRequestAs(CreateEventRequest.class);
        //createEvent
        final EventRecord record = database.createEvent(event
                -> event.title(request.getTitle())
                        .description(request.getDescription())
                        .startTime(request.getStartTime())
                        .endTime(request.getEndTime())
                        .location(request.getLocation())
                );
        database.saveEventRecord(record);
        //Associate with clubs
        for (UUID clubId : request.getClubIds()) {
            database.addEventToClub(eventclub
                    -> eventclub.event(record).club(database.findClub(clubId))
            );
        }
        //Add Parent Event
        if(request.getParentEventId() != null){
            database.addEventToDependOnEvent(dependency ->
                    dependency.eventId(record).dependEventId(database.findEvent(request.getParentEventId())));
        }
        //Add notification
        if(request.getNotification() != null){
            database.createNotification(notification ->
                  notification.event(record).notifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes()).userId(database.findUser(String.valueOf(request.getUserId())))
            );
        }
        return null;

    }
}