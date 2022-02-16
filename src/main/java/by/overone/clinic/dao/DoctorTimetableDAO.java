package by.overone.clinic.dao;

import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.dto.DoctorTimetableDTO;

import java.util.List;

/**
 * Interface with methods for doctor timetable
 * @see by.overone.clinic.dao.impl.DoctorTimetableDAOImpl with realization of all methods
 */
public interface DoctorTimetableDAO {
    void addToDoctorTimetable(DoctorTimetableDTO doctorTimetableDTO);

    DocTimetableDTO getRecordById(long id);

    List<DocTimetableDTO> getAllByDoctorId(long id);

    List<DocTimetableDTO> getRecordByDate(int id, int day, int month, int year);
}
