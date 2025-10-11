package com.fit3161.project.endpoint.attendance.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
public class AttendanceRequest {
    private String firstName;
    private String lastName;
    private String memberType;
    private String email;
    private String notes;
}
//add email and notes