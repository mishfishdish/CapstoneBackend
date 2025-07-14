package com.fit3161.project.endpoint.onboarding.CreateUser.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateResponse {

    private UUID userId;
}