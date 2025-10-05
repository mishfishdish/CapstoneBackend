package com.fit3161.project.database.attendance;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AttendanceRepository extends CrudRepository<AttendanceRecord, UUID> {
    List<AttendanceRecord> findAttendanceRecordsByEvent(EventRecord eventRecord);
}
