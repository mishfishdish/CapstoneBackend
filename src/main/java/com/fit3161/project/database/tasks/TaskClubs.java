package com.fit3161.project.database.tasks;

import com.fit3161.project.database.club.ClubRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Entity
@Table(name = "task_clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
public class TaskClubs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "TASK_ID", nullable = false)
    private TaskRecord task;

    @ManyToOne
    @JoinColumn(name = "CLUB_ID", nullable = false)
    private ClubRecord club;
}
