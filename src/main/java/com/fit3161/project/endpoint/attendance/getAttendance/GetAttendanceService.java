package com.fit3161.project.endpoint.attendance.getAttendance;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.attendance.AttendanceRecord;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.endpoint.attendance.getAttendance.response.Attendee;
import com.fit3161.project.endpoint.attendance.getAttendance.response.GetAttendanceResponse;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
public class GetAttendanceService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public GetAttendanceResponse getResponse() {

        final EventRecord event = database.findEvent(client.getEventId());
        final List<AttendanceRecord> attendanceRecords = database.getAttendanceRecords(event);
        return new GetAttendanceResponse(attendanceRecords.stream()
                .map(record -> new Attendee(record.getFirstName(), record.getLastName(), record.getMemberType().toString()))
                .collect(Collectors.toList()));

    }
}
