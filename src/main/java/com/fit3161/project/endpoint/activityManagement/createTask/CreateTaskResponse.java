package com.fit3161.project.endpoint.activityManagement.createTask;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateTaskResponse {

    private UUID taskId;
}