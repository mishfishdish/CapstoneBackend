package com.fit3161.project.database.event;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "EVENTS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class EventRecord {

    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "END_TIME")
    private LocalDateTime endTime;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "COMPLETED")
    private Boolean completed;

}
