package com.fit3161.project.database.event.repository;

import com.fit3161.project.database.event.daos.EventClubs;
import com.fit3161.project.database.event.daos.EventClubsId;
import com.fit3161.project.database.event.daos.EventDependencies;
import org.springframework.data.repository.CrudRepository;

public interface EventDependencyRepository extends CrudRepository<EventDependencies, Long> {
}
