package com.fit3161.project.database.tasks;

import com.fit3161.project.database.user.UserRecord;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", nullable = false, updatable = false)
    private UUID taskId;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "priority", length = 10)
    private String priority; // consider using an enum for safety

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @Column(name = "completed", nullable = false)
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private UserRecord createdBy;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}