package com.fit3161.project.database.attendance;

import com.fit3161.project.database.event.EventRecord;

import java.util.List;
import java.util.function.Consumer;

public interface AttendanceService {

    AttendanceRepository getAttendanceRepository();

    default AttendanceRecord saveAttendanceRecord(final AttendanceRecord attendanceRecord) {
        return getAttendanceRepository().save(attendanceRecord);
    }

    default AttendanceRecord createAttendanceRecord(final Consumer<AttendanceRecord.AttendanceRecordBuilder> consumer) {
        final AttendanceRecord.AttendanceRecordBuilder attendanceRecordBuilder = new AttendanceRecord.AttendanceRecordBuilder();
        consumer.accept(attendanceRecordBuilder);
        return getAttendanceRepository().save(attendanceRecordBuilder.build());
    }


    default List<AttendanceRecord> getAttendanceRecords(final EventRecord eventRecord) {
        return getAttendanceRepository().findAttendanceRecordsByEvent(eventRecord);
    }

    default void removeAttendanceRecordsByEvent(EventRecord eventRecord) {
        getAttendanceRepository().removeAttendanceRecordsByEvent(eventRecord);

    }
}
