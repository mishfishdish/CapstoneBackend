package com.fit3161.project.database.event.daos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "EVENTS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventRecord {

    @Id
    @Column(name = "EVENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

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

}
