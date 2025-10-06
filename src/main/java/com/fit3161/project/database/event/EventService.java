package com.fit3161.project.database.event;

import com.fit3161.project.endpoint.general.getUserEvents.UserEventSummary;
import com.fit3161.project.endpoint.home.response.EventStats;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface EventService {

    EventClubRepository getEventClubRepository();

    EventDependencyRepository getEventDependencyRepository();

    EventRecordRepository getEventRecordRepository();

    default EventRecord createEvent(Consumer<EventRecord.EventRecordBuilder> consumer) {
        final EventRecord.EventRecordBuilder eventRecordBuilder = new EventRecord.EventRecordBuilder();
        consumer.accept(eventRecordBuilder);
        return getEventRecordRepository().save(eventRecordBuilder.build());
    }

    default EventRecord saveEventRecord(final EventRecord event) {
        return getEventRecordRepository().save(event);
    }

    default EventClubs addEventToClub(final Consumer<EventClubs.EventClubsBuilder> consumer) {
        final EventClubs.EventClubsBuilder event = new EventClubs.EventClubsBuilder();
        consumer.accept(event);
        return getEventClubRepository().save(event.build());
    }

    default EventDependencies addEventToDependOnEvent(final Consumer<EventDependencies.EventDependenciesBuilder> consumer) {
        final EventDependencies.EventDependenciesBuilder dependency = new EventDependencies.EventDependenciesBuilder();
        consumer.accept(dependency);
        return getEventDependencyRepository().save(dependency.build());
    }

    default EventRecord findEvent(UUID eventId) {
        return getEventRecordRepository().findEventRecordByEventId(eventId);
    }

    default void removeClubEvent(EventRecord eventRecord) {
        getEventClubRepository().removeEventClubsByEvent(eventRecord);
    }

    default void removeEventDependencies(EventRecord eventRecord) {
        getEventDependencyRepository().removeEventDependenciesByEventId(eventRecord);
    }

    default void removeEvent(EventRecord eventRecord) {
        getEventRecordRepository().delete(eventRecord);
    }

    default UUID findEventDependency(EventRecord eventRecord) {
        EventDependencies deps = getEventDependencyRepository().findEventDependenciesByEventId(eventRecord);
        if (deps != null) {
            return deps.getDependEventId().getEventId();
        } else {
            return null;
        }
    }

    default Stream<UUID> findEventClubIds(EventRecord eventRecord) {
        return getEventClubRepository().findEventClubsByEvent(eventRecord).stream().map(
                eventClub -> eventClub.getClub().getClubId());
    }

    default EventStats findEventStats(UUID userId) {
        List<Object[]> rows = getEventRecordRepository().findEventCountsForUser(userId);
        Object[] row = rows.get(0);

        EventStats stats = new EventStats();
        stats.setPast(((Number) row[0]).intValue());
        stats.setTotal(((Number) row[1]).intValue());
        return stats;

    }

    default List<UserEventSummary> getEventsForUser(UUID userId) {
        return getEventRecordRepository().findEventsByUserId(userId).stream()
                .map(row -> UserEventSummary.builder()
                        .eventId((UUID) row[0])
                        .title((String) row[1])
                        .build())
                .collect(Collectors.toList());
    }

}
