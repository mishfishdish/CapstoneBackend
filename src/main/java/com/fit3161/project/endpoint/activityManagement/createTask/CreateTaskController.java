package com.fit3161.project.endpoint.activityManagement.createTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fit3161.project.endpoint.activityManagement.createEvent.CreateEventService;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.endpoint.activityManagement.createTask.request.CreateTaskRequest;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CreateTaskController {

    private final CreateTaskService createTaskService;
    private final ClientManager client;

    @PostMapping("/api/tasks")
    public ResponseEntity<String> handler(@Valid @RequestBody CreateTaskRequest request) throws JsonProcessingException {
        client.setRequest(request);
        return new ResponseEntity<>(createTaskService.getResponse(),createTaskService.getStatus());
    }
}