package by.overone.clinic.dao;

import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;

import java.util.List;

/**
 * Interface with methods for record
 * @see by.overone.clinic.dao.impl.RecordDAOImpl with realization of all methods
 */
public interface RecordDAO {
    void makeRecord(long id, RecordDTO recordDTO);

    void deleteRecord(long id);

    void updateRecord(long id, RecordDTO recordDTO);

    List<RecordDTO> getAllRecords();

    Record getRecordById(long id);

    List<RecordDTO> getRecordByClientId(long id);

    List<RecordDTO> getRecordByDoctorId(long id);

    List<Record> getRecordByStatus(String status);
}
