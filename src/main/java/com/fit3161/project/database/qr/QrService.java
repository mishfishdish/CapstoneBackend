package com.fit3161.project.database.qr;

import com.fit3161.project.database.event.EventRecord;

public interface QrService {
    QrRepository getQrRepository();

    default QrRecord findQr(EventRecord eventRecord) {
        return getQrRepository().findQrRecordByEvent(eventRecord);
    }

}
