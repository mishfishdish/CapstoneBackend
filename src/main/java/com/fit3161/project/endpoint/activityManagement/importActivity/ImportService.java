package com.fit3161.project.endpoint.activityManagement.importActivity;

import com.fit3161.project.database.Database;
import com.fit3161.project.endpoint.activityManagement.createEvent.CreateEventService;
import com.fit3161.project.endpoint.activityManagement.createEvent.request.CreateEventRequest;
import com.fit3161.project.endpoint.activityManagement.createTask.CreateTaskService;
import com.fit3161.project.endpoint.activityManagement.createTask.request.CreateTaskRequest;
import com.fit3161.project.endpoint.activityManagement.importActivity.response.FailureDetail;
import com.fit3161.project.endpoint.activityManagement.importActivity.response.ImportResult;
import com.fit3161.project.managers.ClientManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Getter
@Transactional
@RequiredArgsConstructor
public class ImportService {
    private final Database database;
    private final ClientManager client;
    private final CreateEventService createEventService;
    private final CreateTaskService createTaskService;

    public HttpStatus getStatus() {
        return HttpStatus.OK;
    }

    public ImportResult getResponse() {
        List<FailureDetail> failures = new ArrayList<>();
        int successCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getFile().getInputStream()))) {
            CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : parser) {
                try {
                    String type = record.get("type");
                    if ("event".equalsIgnoreCase(type)) {
                        CreateEventRequest request = CreateEventRequest.builder()
                                .title(record.get("title"))
                                .startTime(LocalDateTime.parse(record.get("startTime")))
                                .endTime(LocalDateTime.parse(record.get("startTime")))
                                .location(record.get("location"))
                                .description(record.get("description"))
                                .clubs(Arrays.stream(record.get("clubs").split(":"))
                                        .map(UUID::fromString) // convert String to Integer
                                        .collect(Collectors.toList()))
                                .parentEventId(null)
                                .build();
                        client.setRequest(request);
                        createEventService.createEvent();

                    } else {
                        CreateTaskRequest request = CreateTaskRequest.builder().
                                title(record.get("title"))
                                .description(record.get("description"))
                                .dueDate(LocalDateTime.parse(record.get("startTime")))
                                .clubs(Arrays.stream(record.get("clubs").split(":"))
                                        .map(UUID::fromString) // convert String to Integer
                                        .collect(Collectors.toList()))
                                .parentEvent(null)
                                .priority(record.get("priority"))
                                .build();
                        client.setRequest(request);
                        createTaskService.getResponse();
                    }

                    successCount++;
                } catch (Exception e) {
                    failures.add(new FailureDetail(record.getRecordNumber(), e.getMessage()));
                }
            }
        } catch (IOException e) {
            failures.add(new FailureDetail(0, "Failed to read file"));
        }

        return new ImportResult(successCount, failures.size(), failures);

    }
}
