package com.fit3161.project.endpoint.attendance.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Attendee {
    
    private String firstName;
    private String lastName;
    private String memberType;
}