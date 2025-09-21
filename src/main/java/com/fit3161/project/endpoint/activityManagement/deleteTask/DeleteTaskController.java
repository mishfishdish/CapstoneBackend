package com.fit3161.project.endpoint.activityManagement.deleteTask;

import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DeleteTaskController {

    private final DeleteTaskService deleteTaskService;
    private final ClientManager client;

    @DeleteMapping("/api/task/{taskId}")
    public ResponseEntity<String> handler(@Valid @PathVariable UUID taskId) {
        client.setTaskId(taskId);
        return new ResponseEntity<>(deleteTaskService.getResponse(), deleteTaskService.getStatus());
    }
}