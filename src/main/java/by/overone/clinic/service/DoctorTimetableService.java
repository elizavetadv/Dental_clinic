package by.overone.clinic.service;

import by.overone.clinic.dto.DocTimetableDTO;

import java.util.List;

public interface DoctorTimetableService {
    DocTimetableDTO getRecordById(long id);

    List<DocTimetableDTO> getRecordsByDay(long id, int day);

    List<DocTimetableDTO> getRecordsByMonth(long id, int month);

    List<DocTimetableDTO> getRecordsByYear(long id, int year);

    List<DocTimetableDTO> getAllByDoctorId(long id);
}
