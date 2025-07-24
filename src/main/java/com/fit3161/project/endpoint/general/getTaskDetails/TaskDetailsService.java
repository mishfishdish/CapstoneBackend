package com.fit3161.project.endpoint.general.getTaskDetails;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.tasks.TaskRecord;
import com.fit3161.project.managers.ClientManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Getter
@Transactional
@RequiredArgsConstructor
public class TaskDetailsService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public TaskDetailsResponse getResponse() {

        TaskDetailsResponse.TaskDetailsResponseBuilder response = new TaskDetailsResponse.TaskDetailsResponseBuilder();

        // 1. Fetch the existing event
        TaskRecord existing = database.findTask(client.getTaskId());

        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        response.title(existing.getTitle());
        response.description(existing.getDescription());
        response.dueDate(existing.getDeadline());
        response.completed(existing.isCompleted());
        response.clubs(database.findEventClubIds(existing).toList());
        response.notifyBeforeMinutes(database.findNotification(existing).getNotifyBeforeMinutes());
        response.parentEventId(database.findTaskDependency(existing));

        return response.build();
    }

}
