package com.fit3161.project.database.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRecord, Long> {

    boolean existsByEmailAndPasswordHash(String email, String password);
    UserRecord findUserRecordIdByEmail(String email);

}


