package com.fit3161.project.endpoint.home.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class DashboardResponse {
    private String name;
    private List<Activity> activities;
    private EventStats events;
    private TaskStats tasks;
    private List<String> logs;
}



