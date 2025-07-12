package com.fit3161.project.database.csv;

import java.util.function.Consumer;



public interface CsvService {

    CsvRepository getCsvRepository();

    default CsvRecord saveCsvRepository(final CsvRecord csvRecord){
        return getCsvRepository().save(csvRecord);
    }

    default CsvRecord createCsvRecord(final Consumer<CsvRecord.CsvRecordBuilder> consumer){
        final CsvRecord.CsvRecordBuilder csvRecordBuilder = new CsvRecord.CsvRecordBuilder();
        consumer.accept(csvRecordBuilder);
        return getCsvRepository().save(csvRecordBuilder.build());
    }

}



