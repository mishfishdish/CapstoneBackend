package com.fit3161.project.database.user;

import com.fit3161.project.database.club.ClubRecord;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserClubs {

    @EmbeddedId
    private UserClubId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserRecord user;

    @ManyToOne
    @MapsId("clubId")
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private ClubRecord club;

    @Column(name = "role_in_club", length = 10)
    private String roleInClub; // Optional: turn into enum for 'admin'/'member'

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
    }
