package com.fit3161.project.database.notification;

import com.fit3161.project.database.event.EventRecord;
import com.fit3161.project.database.tasks.TaskRecord;
import com.fit3161.project.database.user.UserRecord;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "NOTIFICATIONS")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRecord {

    @Id
    @Column(name = "NOTIFICATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID notificationId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private UserRecord userId;

    @ManyToOne
    @JoinColumn(name = "TASK_ID", referencedColumnName = "TASK_ID", nullable = false)
    private TaskRecord task;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "EVENT_ID", nullable = false)
    private EventRecord event;

    @Column(name = "NOTIFY_BEFORE_MINUTES")
    private Integer notifyBeforeMinutes;
}
