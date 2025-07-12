package com.fit3161.project.endpoint.onboarding.CreateClub;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.endpoint.onboarding.CreateClub.request.CreateClubRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class CreateClubService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse(){
        final CreateClubRequest request = client.getRequestAs(CreateClubRequest.class);
        final ClubRecord record = database.createClubRecord(club
                -> club.name(request.getName()).description(request.getDescription()));
        database.saveClubRecord(record);
        return null;
    }
}
