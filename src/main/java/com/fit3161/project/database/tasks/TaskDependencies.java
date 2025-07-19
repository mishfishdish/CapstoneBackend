package com.fit3161.project.database.tasks;

import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.event.EventRecord;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "task_dependencies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDependencies {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private TaskRecord task;

    @ManyToOne
    @JoinColumn(name = "club_id", referencedColumnName = "club_id")
    private ClubRecord club;

    @ManyToOne
    @JoinColumn(name = "depends_on_event_id", referencedColumnName = "event_id")
    private EventRecord dependsOnEvent;
}