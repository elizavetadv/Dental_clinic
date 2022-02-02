package by.overone.clinic.service;

import by.overone.clinic.model.Reception;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ReceptionService {
    Reception getById(long id);

    void confirmRecord(long receptionId);

    void updateRecordDone();

    List<Time> getDoctorFreeTime(String doctorType, LocalDate date);
}