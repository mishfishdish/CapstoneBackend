package com.fit3161.project.endpoint.attendance.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class GetAttendanceResponse {

    private List<Attendee> attendees;
}