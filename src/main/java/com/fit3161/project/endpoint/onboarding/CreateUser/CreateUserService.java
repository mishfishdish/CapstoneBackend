package com.fit3161.project.endpoint.onboarding.CreateUser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fit3161.project.database.Database;
import com.fit3161.project.database.user.UserRecord;
import com.fit3161.project.endpoint.onboarding.CreateUser.request.UserRequest;
import com.fit3161.project.endpoint.onboarding.CreateUser.response.CreateResponse;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Getter
@RequiredArgsConstructor
public class CreateUserService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public String getResponse() throws JsonProcessingException {
        final UserRequest request = client.getRequestAs(UserRequest.class);

        // 1️⃣ Check if user already exists by email
        UserRecord existingUser = database.findUser(request.getEmail());
        if (existingUser != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,  // 409
                    "User with this email already exists"
            );
        }

        // 2️⃣ Create new user
        final UserRecord record = database.createUser(user ->
                user.firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .passwordHash(request.getPassword()));

        database.saveUserRecord(record);

        // 3️⃣ Return JSON response
        ObjectMapper mapper = new ObjectMapper();
        CreateResponse response = new CreateResponse(record.getUserId());
        return mapper.writeValueAsString(response);
    }
}