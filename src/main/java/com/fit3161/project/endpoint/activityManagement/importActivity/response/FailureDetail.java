package com.fit3161.project.endpoint.activityManagement.importActivity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailureDetail {
    private long row;
    private String error;
}