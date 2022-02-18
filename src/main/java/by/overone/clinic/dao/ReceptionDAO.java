package by.overone.clinic.dao;

import by.overone.clinic.model.Reception;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface with methods for reception
 * @see by.overone.clinic.dao.impl.ReceptionDAOImpl with realization of all methods
 */
public interface ReceptionDAO {
    void addReception(long recordId);

    Reception getById(long id);

    List<Time> getDoctorFreeTime(String doctorType, LocalDate date);

    long findDoctorId(String type);

    void confirmRecord(long receptionId);

    void updateRecordDone();
}
