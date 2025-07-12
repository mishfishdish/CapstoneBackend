package com.fit3161.project.database.user;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserClubRepository extends CrudRepository<UserClubs, UUID>  {
}
