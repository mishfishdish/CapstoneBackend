package com.fit3161.project.endpoint.activityManagement.importActivity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ImportResult {
    private int successCount;
    private int failureCount;
    private List<FailureDetail> failures;
}