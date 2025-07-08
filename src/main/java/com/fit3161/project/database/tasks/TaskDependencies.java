package com.fit3161.project.database.tasks;

import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.event.daos.EventRecord;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task_dependencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDependencies {

    @EmbeddedId
    private TaskDependencyId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private TaskRecord task;

    @ManyToOne
    @MapsId("clubId")
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private ClubRecord club;

    @ManyToOne
    @JoinColumn(name = "depends_on_event_id", referencedColumnName = "event_id")
    private EventRecord dependsOnEvent;
}