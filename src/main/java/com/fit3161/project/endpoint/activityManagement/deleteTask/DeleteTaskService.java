package com.fit3161.project.endpoint.activityManagement.deleteTask;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.notification.NotificationRecord;
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
public class DeleteTaskService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        TaskRecord task = database.findTask(client.getTaskId());
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        database.removeTaskClub(task);
        database.removeTaskDependencies(task);

        NotificationRecord notification = database.findNotification(task);
        if (notification != null) {
            database.deleteNotification(notification);
        }
        database.removeTask(task);

        return null;
    }}
