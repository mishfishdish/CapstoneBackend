package com.fit3161.project.database.club;

import com.fit3161.project.database.attendance.AttendanceRecord;
import com.fit3161.project.database.attendance.AttendanceRepository;

import java.util.function.Consumer;

public interface ClubService {

    ClubRespository getClubRepository();

    default ClubRecord saveClubRecord(final ClubRecord clubRecord){
        return getClubRepository().save(clubRecord);
    }

    default ClubRecord createClubRecord(final Consumer<ClubRecord.ClubRecordBuilder> consumer){
        final ClubRecord.ClubRecordBuilder clubRecordBuilder = new ClubRecord.ClubRecordBuilder();
        consumer.accept(clubRecordBuilder);
        return getClubRepository().save(clubRecordBuilder.build());
    }

}



