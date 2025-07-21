package com.fit3161.project.endpoint.onboarding.InviteClub;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.user.UserClubs;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.onboarding.InviteClub.request.InviteRequest;
import com.fit3161.project.managers.ClientManager;
import com.fit3161.project.services.EmailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class InviteService {
    private final Database database;
    private final ClientManager client;
    private final EmailService email;
    @Value("${app.jwt-secret}")
    private String secretKey;
    @Value("${invite.endpoint}")
    private String inviteEndpoint;

    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        final InviteRequest request = client.getRequestAs(InviteRequest.class);
        final ClubRecord club = database.findClub(UUID.fromString(request.getClubId()));
        if (database.userExists(request.getEmail())) {
            final UserRecord user = database.findUser(request.getEmail());
            final UserClubs userClubRecord = database.addUserToClub(record
                    -> record.club(club).user(user).roleInClub(request.getRole()));
            database.saveUserClubRecord(userClubRecord);
        } else {
            //Generate token with details
            String token = Jwts.builder()
                    .setSubject("club-invite")
                    .setClaims(Map.of(
                            "clubId", club.getClubId(),
                            "clubName", club.getName(),
                            "role", request.getRole(),
                            "email", request.getEmail()
                    ))
                    .setIssuedAt(new Date())
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .compact();

            //Send email via email server
            email.sendInviteEmail(request.getEmail(), club.getName(), inviteEndpoint + token);

        }


        return null;
    }
}