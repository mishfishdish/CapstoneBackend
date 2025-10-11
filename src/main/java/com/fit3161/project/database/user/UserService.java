package com.fit3161.project.database.user;

import java.util.UUID;
import java.util.function.Consumer;

public interface UserService {
    UserRepository getUserRepository();

    UserClubRepository getUserClubRepository();

    default UserRecord saveUserRecord(final UserRecord userRecord) {
        return getUserRepository().save(userRecord);
    }

    default UserClubs saveUserClubRecord(final UserClubs userClub) {
        return getUserClubRepository().save(userClub);
    }

    default UserRecord createUser(final Consumer<UserRecord.UserRecordBuilder> consumer) {
        final UserRecord.UserRecordBuilder userRecordBuilder = new UserRecord.UserRecordBuilder();
        consumer.accept(userRecordBuilder);
        return getUserRepository().save(userRecordBuilder.build());
    }

    default Boolean userExists(String username, String password) {
        return getUserRepository().existsByEmailAndPasswordHash(username, password);
    }

    default Boolean userExists(String username) {
        return getUserRepository().existsByEmail(username);
    }


    default UserRecord findUser(String username) {
        try {
            return getUserRepository().findByUserId(UUID.fromString(username));
        } catch (Exception e) {
            return getUserRepository().findByEmail(username);
        }
    }

    default UserClubs addUserToClub(final Consumer<UserClubs.UserClubsBuilder> consumer) {
        final UserClubs.UserClubsBuilder userClubsBuilder = new UserClubs.UserClubsBuilder();
        consumer.accept(userClubsBuilder);
        return getUserClubRepository().save(userClubsBuilder.build());
    }

}
