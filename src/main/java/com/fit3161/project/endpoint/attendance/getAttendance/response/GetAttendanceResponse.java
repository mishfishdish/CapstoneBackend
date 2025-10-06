package com.fit3161.project.endpoint.attendance.getAttendance.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetAttendanceResponse {

    private List<Attendee> attendees;
}