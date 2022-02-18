package by.overone.clinic.service;

import by.overone.clinic.dto.DocTimetableDTO;

import java.util.List;

/**
 * @see by.overone.clinic.service.impl.DoctorTimetableServiceImpl with realization of all methods
 */
public interface DoctorTimetableService {
    DocTimetableDTO getRecordById(long id);

    List<DocTimetableDTO> getRecordByDate(int id, int day, int month, int year);

    List<DocTimetableDTO> getAllByDoctorId(long id);
}
