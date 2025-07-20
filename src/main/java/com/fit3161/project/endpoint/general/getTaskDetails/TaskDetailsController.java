package com.fit3161.project.endpoint.general.getTaskDetails;

import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskDetailsController {

    private final TaskDetailsService taskDetailsService;
    private final ClientManager client;

    @GetMapping("/api/tasks/{taskId}")
    public ResponseEntity<TaskDetailsResponse> handler(@Valid @PathVariable UUID taskId) {
        client.setEventId(taskId);
        return new ResponseEntity<>(taskDetailsService.getResponse(), taskDetailsService.getStatus());
    }
}