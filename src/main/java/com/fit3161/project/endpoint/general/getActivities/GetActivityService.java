package com.fit3161.project.endpoint.general.getActivities;

import com.fit3161.project.database.Database;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@RequiredArgsConstructor
public class GetActivityService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.OK;
    }

    public List<ActivityResponse> getResponse(){
        return database.getAllActivities(client.getClubId());
    }
}
