package com.fit3161.project.endpoint.home.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fit3161.project.database.attendance.enums.MemberType;
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
    private MemberType memberType;
    private String email;
    private String notes;
}
//add email and notes