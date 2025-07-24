package com.fit3161.project.endpoint.activityManagement.createEvent;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.qr.QrRecord;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.managers.ClientManager;
import com.fit3161.project.utilities.QrCodeUtil;
import com.google.zxing.WriterException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CreateEventService {
    private final Database database;
    private final ClientManager client;
    @Value("${qr.endpoint}")
    private String qrEndpoint;

    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        createEvent();
        return null;

    }

    public void createEvent() {
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
        for (UUID clubId : request.getClubs()) {
            database.addEventToClub(eventclub
                    -> eventclub.event(record).club(database.findClub(clubId))
            );
        }
        //Add Parent Event
        if (request.getParentEventId() != null) {
            database.addEventToDependOnEvent(dependency ->
                    dependency.eventId(record).dependEventId(database.findEvent(request.getParentEventId())));
        }
        //Add notification
        if (request.getNotification() != null) {
            database.createNotification(notification ->
                    notification.event(record).notifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes()).userId(database.findUser(String.valueOf(request.getUserId())))
            );
        }

        QrRecord qrRecord = database.createQrCode(
                code ->
                {
                    try {
                        code.event(record).qrCode(QrCodeUtil.generateQrCodeBase64(qrEndpoint + "?eventId=" + record.getEventId() + "&eventName=" + record.getTitle()));
                    } catch (WriterException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        database.saveQrRecord(qrRecord);


    }
}