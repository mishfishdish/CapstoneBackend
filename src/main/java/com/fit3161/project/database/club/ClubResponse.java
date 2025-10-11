package com.fit3161.project.database.club;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;


@AllArgsConstructor
@Data
public class ClubResponse {
    String clubName;
    String role;
    UUID clubId;
}
