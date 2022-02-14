package by.overone.clinic.service.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.RecordDAO;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.model.RecordStatus;
import by.overone.clinic.service.DetailsService;
import by.overone.clinic.service.RecordService;
import by.overone.clinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDAO recordDAO;

    @Autowired
    UserService userService;

    @Autowired
    DetailsService detailsService;

    @Override
    public void makeRecord(long id, RecordDTO recordDTO) {
        userService.getUserById(id);
        detailsService.getClientDetails(id);
        recordDAO.makeRecord(id, recordDTO);
    }

    @Override
    public void deleteRecord(long id) {
        getRecordById(id);
        recordDAO.deleteRecord(id);
    }

    @Override
    public void updateRecord(long id, RecordDTO recordDTO) {
        recordDAO.updateRecord(id, recordDTO);
    }

    @Override
    public List<RecordDTO> getAllRecords() {
        return recordDAO.getAllRecords();
    }

    @Override
    public Record getRecordById(long id) {
        return recordDAO.getRecordById(id);
    }

    @Override
    public List<RecordDTO> getRecordByClientId(long id) {
        return recordDAO.getRecordByClientId(id);
    }

    @Override
    public List<RecordDTO> getRecordByDoctorId(long id) {
        return recordDAO.getRecordByDoctorId(id);
    }

    @Override
    public List<RecordDTO> getRecordByStatus(String status) {
        if(!status.equals(RecordStatus.WAITING.toString()) && !status.equals(RecordStatus.CONFIRMED.toString())
                && !status.equals(RecordStatus.CANCELLED.toString()) && !status.equals(RecordStatus.DONE.toString())){
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }
        return recordDAO.getRecordByStatus(status)
                .stream()
                .map(record -> new RecordDTO(record.getDoctorType(), record.getDate(), record.getTime()))
                .collect(Collectors.toList());
    }
}
