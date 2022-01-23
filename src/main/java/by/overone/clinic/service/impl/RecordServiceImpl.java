package by.overone.clinic.service.impl;

import by.overone.clinic.dao.RecordDAO;
import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.dto.RecordUpdatedDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDAO recordDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public void makeRecord(long id, RecordDTO recordDTO) {
        //validation
//        userDAO.getUserById(id);
        //check if user add details
        recordDAO.makeRecord(id, recordDTO);
    }

    @Override
    public void deleteRecord(long id) {
        //check if record exists
        recordDAO.deleteRecord(id);
    }

    @Override
    public void updateRecord(RecordUpdatedDTO recordUpdatedDTO) {
        //validation
        recordDAO.updateRecord(recordUpdatedDTO);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordDAO.getAllRecords();
    }

    @Override
    public Record getRecordById(long id) {
        //exception record is not found
        return recordDAO.getRecordById(id);
    }

    @Override
    public Record getRecordByClientId(long id) {
        return recordDAO.getRecordByClientId(id);
    }

    @Override
    public Record getRecordByDoctorId(long id) {
        return recordDAO.getRecordByDoctorId(id);
    }

    @Override
    public List<Record> getRecordByStatus(String status) {
        return recordDAO.getRecordByStatus(status);
    }
}
