package com.fit3161.project.auditing;

import com.fit3161.project.database.Database;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.RevisionType;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditService {

    @PersistenceContext
    private final EntityManager entityManager;
    private final Database database;


    public List<String> getLatestAuditDescriptions() {
        String sql = """
                    SELECT 'TaskRecord' AS entity_type,
                           t.task_id AS entity_id,
                           r.timestamp AS rev_timestamp,
                           t.revtype AS rev_type
                    FROM tasks_aud t
                    JOIN revinfo r ON t.rev = r.id
                
                    UNION ALL
                
                    SELECT 'EventRecord' AS entity_type,
                           e.event_id AS entity_id,
                           r.timestamp AS rev_timestamp,
                           e.revtype AS rev_type
                    FROM events_aud e
                    JOIN revinfo r ON e.rev = r.id
                
                    ORDER BY rev_timestamp DESC
                    LIMIT 10
                """;

        @SuppressWarnings("unchecked")
        List<Object[]> rows = entityManager
                .createNativeQuery(sql)
                .getResultList();

        List<AuditActivity> activities = rows.stream()
                .map(row -> {
                    String entityType = (String) row[0];
                    String entityId = row[1].toString();
                    Date timestamp = new Date(((Number) row[2]).longValue()); // revision timestamp
                    RevisionType revType = RevisionType.values()[((Number) row[3]).intValue()]; // SAFE

                    return new AuditActivity(entityType, entityId, revType, timestamp);
                })
                .toList();

        return activities.stream()
                .map(this::describeAuditActivity)
                .toList();
    }

    private String describeAuditActivity(AuditActivity activity) {
        String action = switch (activity.getRevisionType()) {
            case ADD -> "created";
            case MOD -> "updated";
            case DEL -> "deleted";
        };

        return switch (activity.getEntityName()) {
            case "TaskRecord" -> {
                var taskId = UUID.fromString(activity.getEntityId());
                var task = taskId != null ? database.findTask(UUID.fromString(activity.getEntityId())) : null;
                yield "Task '" + (task != null ? task.getTitle() : "[unknown]") +
                        "' was " + action;
            }

            case "EventRecord" -> {
                var eventId = UUID.fromString(activity.getEntityId());
                var event = eventId != null ? database.findEvent(UUID.fromString(activity.getEntityId())) : null;
                yield "Event '" + (event != null ? event.getTitle() : "[unknown]") +
                        "' was " + action;
            }

            default -> activity.getEntityId() +
                    " was " + action;
        };
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}