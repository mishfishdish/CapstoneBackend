package com.fit3161.project.database.attendance;

import java.util.function.Consumer;

public interface AttendanceService {

    AttendanceRepository getAttendanceRepository();

    default AttendanceRecord saveAttendanceRecord(final AttendanceRecord attendanceRecord){
        return getAttendanceRepository().save(attendanceRecord);
    }

    default AttendanceRecord createAttendanceRecord(final Consumer<AttendanceRecord.AttendanceRecordBuilder> consumer){
        final AttendanceRecord.AttendanceRecordBuilder attendanceRecordBuilder = new AttendanceRecord.AttendanceRecordBuilder();
        consumer.accept(attendanceRecordBuilder);
        return getAttendanceRepository().save(attendanceRecordBuilder.build());
    }
}
