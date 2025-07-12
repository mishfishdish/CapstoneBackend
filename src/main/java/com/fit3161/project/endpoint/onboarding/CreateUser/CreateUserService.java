package com.fit3161.project.endpoint.onboarding.CreateUser;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.onboarding.CreateUser.request.UserRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class CreateUserService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse(){
        final UserRequest request = client.getRequestAs(UserRequest.class);
        final UserRecord record = database.createUser(user
                -> user.firstName(request.getFirstName()).lastName(request.getLastName()).email(request.getEmail()).passwordHash(request.getPassword()));
        database.saveUserRecord(record);
        return null;
    }
}