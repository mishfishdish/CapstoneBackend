package com.fit3161.project.database.qr;

import com.fit3161.project.database.event.EventRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QrRepository extends CrudRepository<QrRecord, UUID> {
    QrRecord findQrRecordByEvent(EventRecord eventRecord);

}
