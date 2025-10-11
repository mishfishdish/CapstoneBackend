package com.fit3161.project.endpoint.activityManagement.deleteEvent;

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

@Service
@Getter
@Transactional
@RequiredArgsConstructor
public class DeleteEventService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        EventRecord event = database.findEvent(client.getEventId());
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        database.removeQr(event);
        database.removeClubEvent(event);
        database.removeEventDependencies(event);
        database.removeEventDependants(event);
        database.removeTaskDependants(event);
        database.removeAttendanceRecordsByEvent(event);

        NotificationRecord notification = database.findNotification(event);
        if (notification != null) {
            database.deleteNotification(notification);
        }
        database.removeEvent(event);

        return null;
    }
}
