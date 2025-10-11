package com.fit3161.project.endpoint.activityManagement.updateTask;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.notification.NotificationRecord;
import com.fit3161.project.database.tasks.TaskRecord;
import com.fit3161.project.endpoint.activityManagement.updateTask.request.UpdateTaskRequest;
import com.fit3161.project.managers.ClientManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Getter
@Transactional
@RequiredArgsConstructor
public class UpdateTaskService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        final UpdateTaskRequest request = client.getRequestAs(UpdateTaskRequest.class);

        // 1. Fetch the existing event
        TaskRecord existing = database.findTask(client.getTaskId());
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // 2. Update basic fields only if provided
        if (request.getTitle() != null) existing.setTitle(request.getTitle());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getPriority() != null) existing.setPriority(request.getPriority());
        if (request.getDueDate() != null) existing.setDeadline(request.getDueDate());
        if (request.getUserId() != null) existing.setCreatedBy(database.findUser(String.valueOf(request.getUserId())));
        if (request.getCompleted() != null) existing.setCompleted(request.getCompleted());

        database.saveTaskRecord(existing);

        if (request.getClubIds() != null) {
            database.removeTaskClub(existing); // remove all old links
            for (UUID clubId : request.getClubIds()) {
                database.addTaskToClub(taskclub ->
                        taskclub.task(existing).club(database.findClub(clubId)));
            }
        }

        // 4. Update parent event
        database.removeTaskDependencies(existing); // remove existing parent link
        if (request.getParentEventId() != null) {
            database.addTaskToDependOnEvent(dependency ->
                    dependency.task(existing).dependsOnEvent(database.findEvent(request.getParentEventId())));
        }

        // 5. Update or create notification
        if (request.getNotification() != null) {
            NotificationRecord existingNotification = database.findNotification(existing);
            if (existingNotification != null) {
                existingNotification.setNotifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes());
                existingNotification.setUserId(database.findUser(String.valueOf(request.getUserId())));
                database.updateNotification(existingNotification);
            } else {
                database.createNotification(notification ->
                        notification.task(existing)
                                .notifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes())
                                .userId(database.findUser(String.valueOf(request.getUserId())))
                );
            }
        } else {
            NotificationRecord existingNotification = database.findNotification(existing);
            if (existingNotification != null) {
                database.deleteNotification(existingNotification);
            }
        }

        return null;
    }

}
