package com.fit3161.project.endpoint.activityManagement.createTask;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.tasks.TaskRecord;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.endpoint.activityManagement.createTask.request.CreateTaskRequest;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CreateTaskService {
    private final Database database;
    private final ClientManager client;

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }

    public String getResponse() {
        final CreateTaskRequest request = client.getRequestAs(CreateTaskRequest.class);
        //createEvent
        final TaskRecord record = database.createTask(event
                -> event.title(request.getTitle())
                        .description(request.getDescription())
                        .deadline(request.getDueDate())
                        .priority(request.getPriority())
                        .createdBy(database.findUser(String.valueOf(request.getUserId())))
                );

        database.saveTaskRecord(record);
        //Associate with clubs
        for (UUID clubId : request.getClubIds()) {
            database.addTaskToClub(taskclub
                    -> taskclub.task(record).club(database.findClub(clubId))
            );
        }
        //Add Parent Event
        if(request.getParentEventId() != null){
            database.addTaskToDependOnEvent(dependency ->
                    dependency.task(record).dependsOnEvent(database.findEvent(request.getParentEventId())));
        }
        //Add notification
        if(request.getNotification() != null){
            database.createNotification(notification ->
                  notification.task(record).notifyBeforeMinutes(request.getNotification().getNotifyBeforeMinutes()).userId(database.findUser(String.valueOf(request.getUserId())))
            );
        }
        return null;

    }
}