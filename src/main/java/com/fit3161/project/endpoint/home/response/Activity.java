package com.fit3161.project.endpoint.home.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Activity {
    private String title;
    private String type;
    private LocalDateTime time;
}
