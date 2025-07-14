package com.fit3161.project.endpoint.onboarding.SignUser.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SignResponse {

    private UUID userId;
}