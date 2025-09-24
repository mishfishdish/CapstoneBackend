package com.fit3161.project.auditing;

import com.fit3161.project.database.Database;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.tasks.TaskRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditService {

    @PersistenceContext
    private final EntityManager entityManager;
    private final Database database;

    public List<String> getLatestAuditDescriptions() {
        // Fetch N from each entity (slightly > 10 to be safe)
        List<AuditActivity> tasks = getAuditLog(TaskRecord.class, "taskId", 20);
        List<AuditActivity> events = getAuditLog(EventRecord.class, "eventId", 20);

        // Merge efficiently using a priority queue
        PriorityQueue<AuditActivity> pq = new PriorityQueue<>(
                Comparator.comparing(AuditActivity::getRevisionTimestamp).reversed()
        );
        pq.addAll(tasks);
        pq.addAll(events);

        // Extract global top 10
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
            result.add(describeAuditActivity(pq.poll()));
        }
        return result;
    }

    private List<AuditActivity> getAuditLog(Class<?> entityClass, String idField, int maxResults) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        List<Object[]> results = reader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .addOrder(AuditEntity.revisionProperty("timestamp").desc())
                .setMaxResults(maxResults) // fetch slightly more than 10
                .getResultList();

        return results.stream()
                .map(row -> {
                    Object entity = row[0];
                    DefaultRevisionEntity revision = (DefaultRevisionEntity) row[1];
                    RevisionType type = (RevisionType) row[2];

                    String idValue = null;
                    try {
                        idValue = entity.getClass()
                                .getMethod("get" + capitalize(idField))
                                .invoke(entity)
                                .toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return new AuditActivity(
                            entityClass.getSimpleName(),
                            idValue,
                            type,
                            new Date(revision.getTimestamp())
                    );
                })
                .collect(Collectors.toList());
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