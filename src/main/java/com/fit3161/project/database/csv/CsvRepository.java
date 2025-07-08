package com.fit3161.project.database.csv;

import com.fit3161.project.database.club.ClubRecord;
import org.springframework.data.repository.CrudRepository;

public interface CsvRepository extends CrudRepository<CsvRecord, Long> {
}
