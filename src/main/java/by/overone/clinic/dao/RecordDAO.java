package by.overone.clinic.dao;

import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.dto.RecordUpdatedDTO;
import by.overone.clinic.model.Record;

import java.util.List;

public interface RecordDAO {
    void makeRecord(long id, RecordDTO recordDTO);

    void deleteRecord(long id);

    void updateRecord(RecordUpdatedDTO recordUpdatedDTO);

    List<Record> getAllRecords();

    Record getRecordById(long id);

    Record getRecordByClientId(long id);

    Record getRecordByDoctorId(long id);

    List<Record> getRecordByStatus(String status);
}
