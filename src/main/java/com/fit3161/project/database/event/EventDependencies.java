package com.fit3161.project.database.event;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Table(name = "EVENT_DEPENDENCIES")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Audited
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
