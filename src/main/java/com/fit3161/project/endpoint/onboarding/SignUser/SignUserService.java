package com.fit3161.project.endpoint.onboarding.SignUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit3161.project.database.Database;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.onboarding.SignUser.request.SignRequest;
import com.fit3161.project.endpoint.onboarding.SignUser.response.SignResponse;
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
        return HttpStatus.ACCEPTED;
    }

    public String getResponse() throws JsonProcessingException {
        final SignRequest request = client.getRequestAs(SignRequest.class);
        boolean exists = database.userExists(request.getEmail(), request.getPassword());
        if (exists) {
            UserRecord user = database.findUser(request.getEmail());
            ObjectMapper mapper = new ObjectMapper();
            SignResponse signResponse = new SignResponse(user.getUserId());
            String json = mapper.writeValueAsString(signResponse);
            return json;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}