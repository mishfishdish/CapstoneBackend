package com.fit3161.project.database.event;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventDependencyRepository extends CrudRepository<EventDependencies, UUID> {
    void removeEventDependenciesByEventId(EventRecord eventId);

    void removeEventDependenciesByDependEventId(EventRecord eventId);

    EventDependencies findEventDependenciesByEventId(EventRecord event);
}
