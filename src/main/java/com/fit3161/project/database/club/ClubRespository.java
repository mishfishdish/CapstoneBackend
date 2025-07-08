package com.fit3161.project.database.club;

import com.fit3161.project.database.attendance.AttendanceRecord;
import com.fit3161.project.database.attendance.AttendanceRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.function.Consumer;

public interface ClubRespository extends CrudRepository<ClubRecord, Long> {

}

