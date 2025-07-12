package com.fit3161.project.endpoint.onboarding.SignUser.request;

import lombok.Data;

@Data
public class SignRequest {

    private String email;
    private String password;
}