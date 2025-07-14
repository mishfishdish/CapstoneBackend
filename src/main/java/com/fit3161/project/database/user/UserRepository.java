package com.fit3161.project.database.user;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserRecord, UUID> {

    boolean existsByEmailAndPasswordHash(String email, String password);
    UserRecord findUserRecordIdByEmail(String email);

}


