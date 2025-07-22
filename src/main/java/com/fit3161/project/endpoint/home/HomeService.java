package com.fit3161.project.endpoint.home;

import com.fit3161.project.auditing.AuditService;
import com.fit3161.project.database.Database;
import com.fit3161.project.endpoint.home.response.DashboardResponse;
import com.fit3161.project.managers.ClientManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class HomeService {
    private final Database database;
    private final ClientManager client;
    private final AuditService auditService;

    public HttpStatus getStatus() {
        return HttpStatus.NO_CONTENT;
    }

    public DashboardResponse getResponse() {
        DashboardResponse.DashboardResponseBuilder dashboard = DashboardResponse.builder();
        UUID userId = client.getUserId();
        dashboard.activities(database.findUpcoming5Activities(userId));
        dashboard.events(database.findEventStats(userId));
        dashboard.tasks(database.findTasksStats(userId));
        dashboard.logs(auditService.getLatestAuditDescriptions());
        return dashboard.build();
    }
}
