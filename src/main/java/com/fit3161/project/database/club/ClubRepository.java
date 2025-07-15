package com.fit3161.project.database.club;

import com.fit3161.project.database.user.UserRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ClubRepository extends CrudRepository<ClubRecord, UUID> {
    ClubRecord findClubRecordByClubId(UUID clubId);

    @Query(value = """
        SELECT c.club_id AS clubId, c.name AS name
        FROM clubs c
        JOIN user_clubs uc ON c.club_id = uc.club_id
        WHERE uc.user_id = :userId
        """, nativeQuery = true)
    List<Object[]>  findClubNamesByUserId(@Param("userId") UUID userId);
}

