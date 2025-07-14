package com.fit3161.project.database.attendance;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AttendanceRepository extends CrudRepository<AttendanceRecord, UUID> {
}
