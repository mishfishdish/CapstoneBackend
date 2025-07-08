package com.fit3161.project.database.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskClubId implements Serializable {

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "club_id")
    private Long clubId;

    public TaskClubId() {}

    public TaskClubId(Long taskId, Long clubId) {
        this.taskId = taskId;
        this.clubId = clubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskClubId)) return false;
        TaskClubId that = (TaskClubId) o;
        return Objects.equals(taskId, that.taskId) &&
                Objects.equals(clubId, that.clubId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, clubId);
    }

    // Getters and setters (or use Lombok)
}