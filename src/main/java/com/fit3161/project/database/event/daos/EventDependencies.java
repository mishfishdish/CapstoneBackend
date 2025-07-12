package com.fit3161.project.database.event.daos;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "EVENT_CLUBS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventDependencies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord eventId;

    @ManyToOne
    @JoinColumn(name = "DEPENDS_ON_EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord dependEventId;

}
