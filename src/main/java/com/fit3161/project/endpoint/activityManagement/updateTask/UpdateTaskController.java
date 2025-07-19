package com.fit3161.project.endpoint.activityManagement.updateTask;

import com.fit3161.project.endpoint.activityManagement.updateTask.request.UpdateTaskRequest;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UpdateTaskController {

    private final UpdateTaskService updateTaskService;
    private final ClientManager client;

    @PutMapping("/api/tasks/{taskId}")
    public ResponseEntity<String> handler(@Valid @PathVariable UUID taskId, @Valid @RequestBody UpdateTaskRequest request) {
        client.setRequest(request);
        client.setTaskId(taskId);
        return new ResponseEntity<>(updateTaskService.getResponse(),updateTaskService.getStatus());
    }
}