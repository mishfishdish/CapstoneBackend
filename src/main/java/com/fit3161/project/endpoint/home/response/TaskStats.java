package com.fit3161.project.endpoint.home.response;


import lombok.Data;

@Data
public class TaskStats {
    private int completed;
    private int overdue;
    private int notCompleted;
}