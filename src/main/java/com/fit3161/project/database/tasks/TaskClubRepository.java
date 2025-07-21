package com.fit3161.project.database.tasks;

import com.fit3161.project.database.club.ClubRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskClubRepository extends CrudRepository<TaskClubs, UUID> {
    void removeTaskClubsByTask(TaskRecord taskRecord);

    List<TaskClubs> findTaskClubsById(TaskRecord taskRecord);

    @Query("SELECT tc.club FROM TaskClubs tc WHERE tc.task = :task")
    List<ClubRecord> findClubsByTask(@Param("task") TaskRecord task);
}
