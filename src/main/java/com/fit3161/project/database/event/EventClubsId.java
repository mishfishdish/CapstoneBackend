package com.fit3161.project.database.event;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class EventClubsId implements Serializable {

    @Column(name = "EVENT_ID")
    private UUID eventId;

    @Column(name = "CLUB_ID")
    private UUID clubId;

    // Constructors, equals, and hashCode (required for composite keys)
}
