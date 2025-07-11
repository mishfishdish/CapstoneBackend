package com.fit3161.project.database.user;

import com.fit3161.project.database.club.ClubRecord;

import java.util.function.Consumer;

public interface UserService {
    UserClubRepository getUserClubsRepository();
    UserRepository getUserRepository();

    default UserRecord createUserRecord(final Consumer<UserRecord.UserRecordBuilder> consumer) {
        final UserRecord.UserRecordBuilder userRecordBuilder = new UserRecord.UserRecordBuilder();
        consumer.accept(userRecordBuilder);
        return userRecordBuilder.build();
    }

    default UserClubs createUserClubs(final Consumer<UserClubs.UserClubsBuilder> consumer) {
        final UserClubs.UserClubsBuilder userClubsBuilder = new UserClubs.UserClubsBuilder();
        consumer.accept(userClubsBuilder);
        return userClubsBuilder.build();
    }

    default UserRecord saveUser(final UserRecord userRecord){
        return getUserRepository().save(userRecord);
    }

    default UserClubs saveUserToClub(final UserClubs userClubs){
        return getUserClubsRepository().save(userClubs);
    }

}
