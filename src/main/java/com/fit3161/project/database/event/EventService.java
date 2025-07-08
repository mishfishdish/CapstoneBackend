package com.fit3161.project.database.event;

import com.fit3161.project.database.csv.CsvRecord;
import com.fit3161.project.database.csv.CsvRepository;
import com.fit3161.project.database.event.repository.EventClubRepository;
import com.fit3161.project.database.event.repository.EventDependencyRepository;
import com.fit3161.project.database.event.repository.EventRecordRepository;

import java.util.function.Consumer;

public interface EventService {

    EventClubRepository getEventClubRepository();
    EventDependencyRepository getEventDependencyRepository();
    EventRecordRepository getEventRecordRepository();


}
