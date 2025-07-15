package com.fit3161.project.database.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserRecord, UUID> {

    boolean existsByEmailAndPasswordHash(String email, String password);
    boolean existsByEmail(String email);

    @Query("""
    SELECT u FROM UserRecord u 
    WHERE u.email = :value OR CAST(u.userId AS string) = :value
    """)
    Optional<UserRecord> findUserRecordIdByEmailOrUserId(@Param("value") String value);

}


