package by.overone.clinic.dao;

import by.overone.clinic.model.Reception;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public interface ReceptionDAO {
    void addReception(long recordId);

    Reception getById(long id);

    List<Time> getDoctorFreeTime(String doctorType, LocalDate date);

    long findDoctorId(String type);

    void confirmRecord(long receptionId);

    void updateRecordDone();
}
