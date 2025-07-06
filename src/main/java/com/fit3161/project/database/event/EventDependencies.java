package com.fit3161.project.database.event;

import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord eventId;

    @ManyToOne
    @JoinColumn(name = "DEPENDS_ON_EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord dependEventId;

}
