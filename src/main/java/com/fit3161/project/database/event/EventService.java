package com.fit3161.project.database.event;

import com.fit3161.project.database.user.UserClubs;
import com.fit3161.project.database.user.UserRecord;

import java.util.UUID;
import java.util.function.Consumer;

public interface EventService {

    EventClubRepository getEventClubRepository();
    EventDependencyRepository getEventDependencyRepository();
    EventRecordRepository getEventRecordRepository();

    default EventRecord createEvent(Consumer<EventRecord.EventRecordBuilder> consumer){
        final EventRecord.EventRecordBuilder eventRecordBuilder = new EventRecord.EventRecordBuilder();
        consumer.accept(eventRecordBuilder);
        return getEventRecordRepository().save(eventRecordBuilder.build());
    }

    default EventRecord saveEventRecord(final EventRecord event){
        return getEventRecordRepository().save(event);
    }

    default EventClubs addEventToClub(final Consumer<EventClubs.EventClubsBuilder> consumer){
        final EventClubs.EventClubsBuilder event = new EventClubs.EventClubsBuilder();
        consumer.accept(event);
        return getEventClubRepository().save(event.build());
    }

    default EventDependencies addEventToDependOnEvent(final Consumer<EventDependencies.EventDependenciesBuilder> consumer){
        final EventDependencies.EventDependenciesBuilder dependency = new EventDependencies.EventDependenciesBuilder();
        consumer.accept(dependency);
        return getEventDependencyRepository().save(dependency.build());
    }

    default EventRecord findEvent(UUID eventId){
        return getEventRecordRepository().findEventRecordByEventId(eventId);
    }

    default void removeEvent(EventRecord eventRecord){
        getEventClubRepository().removeEventClubsByEvent(eventRecord);
    }
    default void removeEventDependencies(EventRecord eventRecord){
        getEventDependencyRepository().removeEventDependenciesByEvent(eventRecord);
    }

}
