package by.overone.clinic.service;

import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;

import java.util.List;

public interface RecordService {
    void makeRecord(long id, RecordDTO recordDTO);

    void deleteRecord(long id);

    void updateRecord(long id, RecordDTO recordDTO);

    List<RecordDTO> getAllRecords();

    Record getRecordById(long id);

    List<RecordDTO> getRecordByClientId(long id);

    List<RecordDTO> getRecordByDoctorId(long id);

    List<RecordDTO> getRecordByStatus(String status);
}
