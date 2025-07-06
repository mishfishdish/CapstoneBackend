package com.fit3161.project.database.event;

import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.user.UserRecord;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "EVENT_CLUBS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EventClubs {

    @EmbeddedId
    private EventClubsId id;

    @ManyToOne
    @Column(name = "EVENT_ID")
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord event;

    @ManyToOne
    @Column(name = "CLUB_ID")
    @JoinColumn(name = "CLUB_ID", referencedColumnName = "CLUB_ID", nullable = false)
    private ClubRecord club;

}

