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

    /**
     * This method adds record to database
     *
     * @param id client id
     * @param recordDTO needed record data
     */
    @Override
    public void makeRecord(long id, RecordDTO recordDTO) {
        userService.getUserById(id);
        detailsService.getClientDetails(id);
        recordDAO.makeRecord(id, recordDTO);
    }

    /**
     * Method deletes record in database
     *
     * @param id record id
     */
    @Override
    public void deleteRecord(long id) {
        getRecordById(id);
        recordDAO.deleteRecord(id);
    }

    /**
     * Method for updating record by client
     *
     * @param id record id
     * @param recordDTO record data that can be changed
     */
    @Override
    public void updateRecord(long id, RecordDTO recordDTO) {
        recordDAO.updateRecord(id, recordDTO);
    }

    /**
     * Return all records from database
     *
     * @return list of records
     */
    @Override
    public List<RecordDTO> getAllRecords() {
        return recordDAO.getAllRecords();
    }

    /**
     * Return record by id from db
     *
     * @param id record id
     * @return record
     */
    @Override
    public Record getRecordById(long id) {
        return recordDAO.getRecordById(id);
    }

    /**
     * Return list of records for client by his id
     *
     * @param id client id
     * @return list of records
     */
    @Override
    public List<RecordDTO> getRecordByClientId(long id) {
        return recordDAO.getRecordByClientId(id);
    }

    /**
     * Return list of records for doctor by his id
     *
     * @param id doctor id
     * @return list of records
     */
    @Override
    public List<RecordDTO> getRecordByDoctorId(long id) {
        return recordDAO.getRecordByDoctorId(id);
    }

    /**
     * Return all records by status from db
     * It thows DAOIncorrectException if status is incorrect
     *
     * @param status record status
     * @return list of records
     */
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
