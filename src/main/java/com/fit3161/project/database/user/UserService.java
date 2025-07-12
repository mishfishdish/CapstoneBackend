package com.fit3161.project.database.user;

import com.fit3161.project.database.club.ClubRecord;
import com.fit3161.project.database.user.UserRepository;
import com.fit3161.project.database.user.UserClubRepository;

import java.util.function.Consumer;

public interface UserService {
    UserRepository getUserRepository();
    UserClubRepository getUserClubRepository();

    default UserRecord saveUserRecord(final UserRecord userRecord){
        return getUserRepository().save(userRecord);
    }
    default UserClubs saveUserClubRecord(final UserClubs userClub){
        return getUserClubRepository().save(userClub);
    }

    default UserRecord createUser(final Consumer<UserRecord.UserRecordBuilder> consumer){
        final UserRecord.UserRecordBuilder userRecordBuilder = new UserRecord.UserRecordBuilder();
        consumer.accept(userRecordBuilder);
        return getUserRepository().save(userRecordBuilder.build());
    }

    default Boolean userExists(String username, String password){
        return getUserRepository().existsByEmailAndPasswordHash(username, password);
    }

    default UserClubs addUserToClub(final Consumer<UserClubs.UserClubsBuilder> consumer){
        final UserClubs.UserClubsBuilder userClubsBuilder = new UserClubs.UserClubsBuilder();
        consumer.accept(userClubsBuilder);
        return getUserClubRepository().save(userClubsBuilder.build());
    }

}
