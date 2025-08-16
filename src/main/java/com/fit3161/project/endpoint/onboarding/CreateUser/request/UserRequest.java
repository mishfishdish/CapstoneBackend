package com.fit3161.project.endpoint.onboarding.CreateUser.request;

import lombok.Data;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}