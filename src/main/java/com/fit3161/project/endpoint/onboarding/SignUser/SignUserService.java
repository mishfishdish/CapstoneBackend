package com.fit3161.project.endpoint.onboarding.SignUser;

import com.fit3161.project.database.Database;
import com.fit3161.project.endpoint.onboarding.SignUser.request.SignRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Getter
@RequiredArgsConstructor
public class SignUserService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse(){
        final SignRequest request = client.getRequestAs(SignRequest.class);
        boolean exists = database.userExists(request.getEmail(), request.getPassword());
        if (exists) {
            return null;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}