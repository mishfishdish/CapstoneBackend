package com.fit3161.project.database.event.repository;

import com.fit3161.project.database.csv.CsvRecord;
import com.fit3161.project.database.event.daos.EventClubs;
import com.fit3161.project.database.event.daos.EventClubsId;
import org.springframework.data.repository.CrudRepository;

public interface EventClubRepository extends CrudRepository<EventClubs, EventClubsId> {
}
