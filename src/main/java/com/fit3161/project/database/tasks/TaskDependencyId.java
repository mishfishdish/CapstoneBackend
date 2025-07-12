package com.fit3161.project.database.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class TaskDependencyId implements Serializable {

    @Column(name = "task_id")
    private UUID taskId;

    @Column(name = "club_id")
    private UUID clubId;

    public TaskDependencyId() {}

    public TaskDependencyId(UUID taskId, UUID clubId) {
        this.taskId = taskId;
        this.clubId = clubId;
    }

    // equals and hashCode are required for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskDependencyId)) return false;
        TaskDependencyId that = (TaskDependencyId) o;
        return Objects.equals(taskId, that.taskId) &&
                Objects.equals(clubId, that.clubId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, clubId);
    }

    // getters and setters
}