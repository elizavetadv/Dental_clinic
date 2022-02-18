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

    /**
     * Return record from doctor timetable
     *
     * @param id record id
     * @return record
     */
    @Override
    public DocTimetableDTO getRecordById(long id) {
        return doctorTimetableDAO.getRecordById(id);
    }

    /**
     * Return records by doctor id and whole or part date
     *
     * @param id doctor id
     * @param day day
     * @param month month
     * @param year year
     * @return list of records corresponding to the passed parameters
     */
    @Override
    public List<DocTimetableDTO> getRecordByDate(int id, int day, int month, int year) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getRecordByDate(id, day, month, year);
    }

    /**
     * Return all records for one doctor from doctor timetable
     *
     * @param id doctor id
     * @return records
     */
    @Override
    public List<DocTimetableDTO> getAllByDoctorId(long id) {
        detailsService.getDoctorDetails(id);
        return doctorTimetableDAO.getAllByDoctorId(id);
    }
}
