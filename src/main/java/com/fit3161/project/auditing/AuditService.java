package com.fit3161.project.auditing;

import com.fit3161.project.database.event.*;
import com.fit3161.project.database.tasks.*;
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

    private final TaskRecordRepository taskRepository;
    private final TaskClubRepository taskClubRepository;
    private final TaskDependencyRepository taskDependencyRepository;
    private final EventRecordRepository eventRepository;
    private final EventClubRepository eventClubRepository;
    private final EventDependencyRepository eventDependencyRepository;

    public List<String> getLatestAuditDescriptions() {
        List<AuditActivity> all = new ArrayList<>();

        all.addAll(getAuditLog(TaskRecord.class, "taskId"));
        all.addAll(getAuditLog(TaskClubs.class, "id"));
        all.addAll(getAuditLog(TaskDependencies.class, "id"));
        all.addAll(getAuditLog(EventRecord.class, "eventId"));
        all.addAll(getAuditLog(EventClubs.class, "id"));
        all.addAll(getAuditLog(EventDependencies.class, "id"));

        return all.stream()
                .sorted(Comparator.comparing(AuditActivity::getRevisionTimestamp).reversed())
                .limit(10)
                .map(this::describeAuditActivity)
                .collect(Collectors.toList());
    }

    private List<AuditActivity> getAuditLog(Class<?> entityClass, String idField) {
        AuditReader reader = AuditReaderFactory.get(entityManager);

        List<Object[]> results = reader.createQuery()
                .forRevisionsOfEntity(entityClass, false, true)
                .addOrder(AuditEntity.revisionProperty("timestamp").desc())
                .setMaxResults(10)
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
                                .invoke(entity).toString();
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
            case "Task", "TaskClub" -> {
                var taskId = switch (activity.getEntityName()) {
                    case "Task" -> UUID.fromString(activity.getEntityId());
                    case "TaskClub" -> {
                        var tc = taskClubRepository.findById(UUID.fromString(activity.getEntityId())).orElse(null);
                        yield tc != null ? tc.getTask().getTaskId() : null;
                    }
                    default -> null;
                };
                var task = taskId != null ? taskRepository.findById(taskId).orElse(null) : null;
                yield "Task '" + (task != null ? task.getTitle() : "[unknown]") +
                        "' was " + action + " at " + activity.getRevisionTimestamp();
            }

            case "Event", "EventClub", "EventDependency" -> {
                var eventId = switch (activity.getEntityName()) {
                    case "Event" -> UUID.fromString(activity.getEntityId());
                    case "EventClub" -> {
                        var ec = eventClubRepository.findById(UUID.fromString(activity.getEntityId())).orElse(null);
                        yield ec != null ? ec.getEvent().getEventId() : null;
                    }
                    default -> null;
                };
                var event = eventId != null ? eventRepository.findById(eventId).orElse(null) : null;
                yield "Event '" + (event != null ? event.getTitle() : "[unknown]") +
                        "' was " + action + " at " + activity.getRevisionTimestamp();
            }

            default -> activity.getEntityName() + " with ID " + activity.getEntityId() +
                    " was " + action + " at " + activity.getRevisionTimestamp();
        };
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}