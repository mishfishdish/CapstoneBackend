package com.fit3161.project.auditing;

import com.fit3161.project.database.club.ClubRecord;
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
            case "Task" -> {
                var task = taskRepository.findById(UUID.fromString(activity.getEntityId())).orElse(null);
                var clubs = taskClubRepository.findClubsByTask(task).stream()
                        .map(ClubRecord::getName).toList();

                yield "Task '" + (task != null ? task.getTitle() : "[unknown]") +
                        (clubs.isEmpty() ? "" : "' for Club(s) " + clubs) +
                        "' was " + action + " at " + activity.getRevisionTimestamp();
            }
            case "TaskClub" -> {
                var tc = taskClubRepository.findById(UUID.fromString(activity.getEntityId())).orElse(null);
                yield "Task '" + (tc != null ? tc.getTask().getTitle() : "[unknown]") +
                        "' was " + action + " for Club '" + (tc != null ? tc.getClub().getName() : "[unknown]") +
                        "' at " + activity.getRevisionTimestamp();
            }
            case "Event" -> {
                var event = eventRepository.findById(UUID.fromString(activity.getEntityId())).orElse(null);
                var clubs = eventClubRepository.findClubsByEvent(event).stream()
                        .map(ClubRecord::getName).toList();
                yield "Event '" + (event != null ? event.getTitle() : "[unknown]") +
                        (clubs.isEmpty() ? "" : "' for Club(s) " + clubs) +
                        "' was " + action + " at " + activity.getRevisionTimestamp();
            }
            case "EventClub" -> {
                var ec = eventClubRepository.findById(UUID.fromString(activity.getEntityId())).orElse(null);
                yield "Event '" + (ec != null ? ec.getEvent().getTitle() : "[unknown]") +
                        "' was " + action + " for Club '" + (ec != null ? ec.getClub().getName() : "[unknown]") +
                        "' at " + activity.getRevisionTimestamp();
            }
            default ->
                    activity.getEntityName() + " with ID " + activity.getEntityId() + " was " + action + " at " + activity.getRevisionTimestamp();
        };
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}