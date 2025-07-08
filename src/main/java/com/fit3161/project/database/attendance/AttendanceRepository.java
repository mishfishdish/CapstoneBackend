package com.fit3161.project.database.attendance;

import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends CrudRepository<AttendanceRecord, Long> {
}
