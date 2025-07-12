package com.fit3161.project.endpoint.onboarding.InviteClub.request;

import lombok.Data;

@Data
public class InviteRequest {

    private String email;
    private String clubId;
    private String role;
}