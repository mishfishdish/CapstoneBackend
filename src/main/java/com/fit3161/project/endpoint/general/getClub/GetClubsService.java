package com.fit3161.project.endpoint.general.getClub;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.club.ClubResponse;
import com.fit3161.project.database.user.UserClubs;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.onboarding.AddToClub.request.AddClubRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GetClubsService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.OK;
    }

    public List<ClubResponse> getResponse(){
        return database.getClubFromUser(client.getUserId());
    }
}
