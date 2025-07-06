package com.fit3161.project.database.event;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EventClubsId implements Serializable {

    @Column(name = "EVENT_ID")
    private Long eventId;

    @Column(name = "CLUB_ID")
    private Long clubId;

    // Constructors, equals, and hashCode (required for composite keys)
}
