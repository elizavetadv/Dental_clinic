package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.ReceptionDAO;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.model.DoctorType;
import by.overone.clinic.model.Reception;
import by.overone.clinic.model.Role;
import by.overone.clinic.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ReceptionServiceImpl implements ReceptionService {
    @Autowired
    ReceptionDAO receptionDAO;

    @Override
    public Reception getById(long id) {
        return receptionDAO.getById(id);
    }

    @Override
    public void confirmRecord(long receptionId) {
        getById(receptionId);
        receptionDAO.confirmRecord(receptionId);
    }

    @Override
    public void updateRecordDone() {
        receptionDAO.updateRecordDone();
    }

    @Override
    public List<Time> getDoctorFreeTime(String doctorType, LocalDate date) {
        if(!doctorType.equals(DoctorType.SURGEON.toString().toLowerCase())
                && !doctorType.equals(DoctorType.ORTHOPEDIST.toString().toLowerCase())
                && !doctorType.equals(DoctorType.ORTHODONTIST.toString().toLowerCase())){
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
        return receptionDAO.getDoctorFreeTime(doctorType, date);
    }
}
