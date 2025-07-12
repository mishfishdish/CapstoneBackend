package com.fit3161.project.endpoint.onboarding.AddToClub;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.user.UserClubs;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.onboarding.AddToClub.request.AddClubRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class AddToClubService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse(){
        final AddClubRequest request = client.getRequestAs(AddClubRequest.class);

        final UserRecord user = database.findUser(request.getUsername());

        final ClubRecord club = database.findClub(request.getClubId());

        final UserClubs userClubRecord = database.addUserToClub(record
                -> record.club(club).user(user).roleInClub(request.getRole()));

        database.saveUserClubRecord(userClubRecord);
        return null;
    }
}
