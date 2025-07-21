package com.fit3161.project.database.event;

import com.fit3161.project.database.club.ClubRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EventClubRepository extends CrudRepository<EventClubs, UUID> {

    void removeEventClubsByEvent(EventRecord eventRecord);

    List<EventClubs> findEventClubsByEvent(EventRecord eventRecord);

    @Query("SELECT ec.club FROM EventClubs ec WHERE ec.event = :event")
    List<ClubRecord> findClubsByEvent(@Param("event") EventRecord eventRecord);

}
