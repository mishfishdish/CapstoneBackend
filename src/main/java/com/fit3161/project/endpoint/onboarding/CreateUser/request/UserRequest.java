package com.fit3161.project.endpoint.onboarding.CreateUser.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}