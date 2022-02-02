package by.overone.clinic.dao;

import by.overone.clinic.model.Reception;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ReceptionDAO {
    void addReception(long recordId);

    Reception getById(long id);

    List<Time> getDoctorFreeTime(String doctorType, LocalDate date);

    long findDoctorId(String type);

    boolean checkDateAndTime(long doctorId, LocalDate date, Time time);

    void confirmRecord(long receptionId);

    void updateRecordDone();
}
