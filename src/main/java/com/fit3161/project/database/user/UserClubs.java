package com.fit3161.project.database.user;

import com.fit3161.project.database.club.ClubRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserClubs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserRecord user;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private ClubRecord club;

    @Column(name = "role_in_club", length = 10)
    private String roleInClub; // Optional: turn into enum for 'admin'/'member'

    @Column(name = "joined_at")
    @CreationTimestamp
    private LocalDateTime joinedAt;


}
