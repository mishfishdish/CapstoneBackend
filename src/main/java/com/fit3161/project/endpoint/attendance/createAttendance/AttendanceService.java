package com.fit3161.project.endpoint.attendance;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.attendance.AttendanceRecord;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.endpoint.attendance.request.AttendanceRequest;
import com.fit3161.project.endpoint.attendance.response.CreateResponse;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class AttendanceService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public CreateResponse getResponse() {
        final AttendanceRequest request = client.getRequestAs(AttendanceRequest.class);
        final EventRecord event = database.findEvent(client.getEventId());
        final AttendanceRecord record = database.createAttendanceRecord(attendance
                -> attendance.event(event).firstName(request.getFirstName()).lastName(request.getLastName()).memberType(request.getMemberType()));
        database.saveAttendanceRecord(record);
        return null;
    }
}
