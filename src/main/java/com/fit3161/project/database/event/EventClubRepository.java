package com.fit3161.project.database.event;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventClubRepository extends CrudRepository<EventClubs, EventClubsId> {

    void removeEventClubsByEvent(EventRecord eventRecord);
}
