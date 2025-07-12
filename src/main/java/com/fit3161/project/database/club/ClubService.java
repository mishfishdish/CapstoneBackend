package com.fit3161.project.database.club;

import java.util.UUID;
import java.util.function.Consumer;

public interface ClubService {

    ClubRepository getClubRepository();

    default ClubRecord saveClubRecord(final ClubRecord clubRecord){
        return getClubRepository().save(clubRecord);
    }

    default ClubRecord createClubRecord(final Consumer<ClubRecord.ClubRecordBuilder> consumer){
        final ClubRecord.ClubRecordBuilder clubRecordBuilder = new ClubRecord.ClubRecordBuilder();
        consumer.accept(clubRecordBuilder);
        return getClubRepository().save(clubRecordBuilder.build());
    }

    default ClubRecord findClub(final UUID clubId){
        return getClubRepository().findClubRecordByClubId(clubId);
    }

}



