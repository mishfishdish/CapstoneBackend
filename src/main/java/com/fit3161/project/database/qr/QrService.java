package com.fit3161.project.database.qr;

import com.fit3161.project.database.event.EventRecord;

import java.util.function.Consumer;

public interface QrService {
    QrRepository getQrRepository();

    default QrRecord findQr(EventRecord eventRecord) {
        return getQrRepository().findQrRecordByEvent(eventRecord);
    }

    default void removeQr(EventRecord eventRecord) {
        getQrRepository().deleteQrRecordByEvent(eventRecord);
    }


    default QrRecord createQrCode(Consumer<QrRecord.QrRecordBuilder> consumer) {
        final QrRecord.QrRecordBuilder qrBuilder = new QrRecord.QrRecordBuilder();
        consumer.accept(qrBuilder);
        return getQrRepository().save(qrBuilder.build());
    }

    default QrRecord saveQrRecord(final QrRecord qrRecord) {
        return getQrRepository().save(qrRecord);
    }

}
