package com.fit3161.project.database.event;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventDependencyRepository extends CrudRepository<EventDependencies, UUID> {
    void removeEventDependenciesByEvent(EventRecord eventRecord);
}
