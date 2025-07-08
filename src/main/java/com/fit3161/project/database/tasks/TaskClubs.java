package com.fit3161.project.database.tasks;

import com.fit3161.project.database.club.ClubRecord;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskClubs {

    @EmbeddedId
    private TaskClubId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private TaskRecord task;

    @ManyToOne
    @MapsId("clubId")
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private ClubRecord club;
}
