package com.fit3161.project.database.club;

import com.fit3161.project.database.user.UserRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClubRepository extends CrudRepository<ClubRecord, Long> {
    ClubRecord findClubRecordByClubId(UUID clubId);

}

