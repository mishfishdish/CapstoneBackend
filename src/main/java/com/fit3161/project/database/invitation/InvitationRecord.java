package com.fit3161.project.database.invitation;

import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.event.EventClubsId;
import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.invitation.enums.Role;
import com.fit3161.project.database.invitation.enums.Status;
import com.fit3161.project.database.user.UserRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLUB_INVITATIONS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationRecord {

    @Id
    @Column(name = "INVITATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long invitationId;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @Column(name = "CLUB_ID")
    @JoinColumn(name = "CLUB_ID", referencedColumnName = "CLUB_ID", nullable = false)
    private ClubRecord club;

    @Column(name = "ROLE_OFFERED")
    private Role role;

    @ManyToOne
    @Column(name = "INVITED_BY")
    @JoinColumn(name = "INVITED_BY", referencedColumnName = "USER_ID", nullable = false)
    private UserRecord user;

    @Column(name = "STATUS")
    private Status status;

    @Column(name = "SENT_AT")
    private LocalDateTime sentAt;

    @Column(name = "RESPONDED_AT")
    private LocalDateTime respondedAt;

}