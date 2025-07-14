package com.fit3161.project.database.csv;

import com.fit3161.project.database.club.ClubRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CsvRepository extends CrudRepository<CsvRecord, UUID> {
}
