package com.fit3161.project.database.club;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

    default List<ClubResponse> getClubFromUser(final UUID userId){
        return getClubRepository().findClubNamesByUserId(userId).stream()
                .map(row -> new ClubResponse((String) row[1], (UUID) row[0]))
                .collect(Collectors.toList());
    }

}



