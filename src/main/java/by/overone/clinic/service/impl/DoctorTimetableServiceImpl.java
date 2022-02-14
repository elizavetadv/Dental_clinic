package by.overone.clinic.service.impl;

import by.overone.clinic.dao.DoctorTimetableDAO;
import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.service.DetailsService;
import by.overone.clinic.service.DoctorTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorTimetableServiceImpl implements DoctorTimetableService {

    @Autowired
    DoctorTimetableDAO doctorTimetableDAO;

    @Autowired
    DetailsService detailsService;

    @Override
    public DocTimetableDTO getRecordById(long id) {
        return doctorTimetableDAO.getRecordById(id);
    }

    @Override
    public List<DocTimetableDTO> getRecordsByDay(long id, int day) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getRecordsByDay(id, day);
    }

    @Override
    public List<DocTimetableDTO> getRecordsByMonth(long id, int month) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getRecordsByMonth(id, month);
    }

    @Override
    public List<DocTimetableDTO> getRecordsByYear(long id, int year) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getRecordsByYear(id, year);
    }

    @Override
    public List<DocTimetableDTO> getRecordByDate(int id, int day, int month, int year) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getRecordByDate(id, day, month, year);
    }

    @Override
    public List<DocTimetableDTO> getAllByDoctorId(long id) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getAllByDoctorId(id);
    }
}
