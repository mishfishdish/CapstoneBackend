package com.fit3161.project.endpoint.onboarding.AddToClub.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
public class AddClubRequest {
    private UUID clubId;
    private String username;
    private String role;
}
