package com.fit3161.project.database.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserRecord, UUID> {

    boolean existsByEmailAndPasswordHash(String email, String password);

    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
    UserRecord findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users u WHERE u.user_id = :uuid", nativeQuery = true)
    UserRecord findByUserId(@Param("uuid") UUID uuid);
}


