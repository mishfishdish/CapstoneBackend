package com.fit3161.project.endpoint.attendance.getAttendance.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Attendee {

    private String firstName;
    private String lastName;
    private String memberType;
}