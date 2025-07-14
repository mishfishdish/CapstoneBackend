package com.fit3161.project.database.event.daos;

import com.fit3161.project.database.club.ClubRecord;
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
public class EventClubs {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private EventRecord event;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID", nullable = false)
    private ClubRecord club;

}

