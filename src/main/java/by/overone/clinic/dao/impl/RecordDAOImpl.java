package by.overone.clinic.dao.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.ReceptionDAO;
import by.overone.clinic.dao.RecordDAO;
import by.overone.clinic.dao.exception.DAONotExistException;
import by.overone.clinic.dao.exception.DAORecordException;
import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.model.RecordStatus;
import by.overone.clinic.util.constant.RecordConstant;
import by.overone.clinic.util.constant.UserConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordDAOImpl implements RecordDAO {

    public final JdbcTemplate jdbcTemplate;
    private final ReceptionDAO receptionDAO;

    public static final String ADD_RECORD_QUERY = "INSERT INTO " + RecordConstant.TABLE_RECORD +
            " VALUES(0, ?, ?, ?, ?, ?, 0)";

    public static final String UPDATE_RECORD_STATUS_QUERY = "UPDATE " + RecordConstant.TABLE_RECORD + " SET " +
            UserConstant.STATUS + "=? WHERE " + RecordConstant.RECORD_ID + "=?";

    private final static String UPDATE_RECORD_QUERY = "UPDATE " + RecordConstant.TABLE_RECORD + " SET " +
            RecordConstant.DOCTOR_TYPE + "=COALESCE(?," + RecordConstant.DOCTOR_TYPE + "), " +
            RecordConstant.DATE + "=COALESCE(?," + RecordConstant.DATE + "), " +
            RecordConstant.TIME + "=COALESCE(?," + RecordConstant.TIME + "), " + UserConstant.STATUS + "='WAITING' " +
            "WHERE " + RecordConstant.RECORD_ID + "=?";

    private final static String GET_ALL_RECORDS_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD;

    private final static String GET_RECORD_BY_ID_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.RECORD_ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_CLIENT_ID_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.CLIENT_ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_CLIENT_ID_DATE_TIME_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.CLIENT_ID + "=? AND " + RecordConstant.DATE + "=? AND " + RecordConstant.TIME + "=? AND "
            + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_DOCTOR_ID_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.DOCTOR_ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_STATUS_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD +
            " WHERE " + UserConstant.STATUS + "=?";

    @Override
    public void makeRecord(long id, RecordDTO recordDTO) {
        List<Time> times = receptionDAO.getDoctorFreeTime(recordDTO.getDoctorType(), recordDTO.getDate());

        if (!times.contains(recordDTO.getTime())) {
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_TIME.getErrorCode());
        } else {
            jdbcTemplate.update(ADD_RECORD_QUERY, recordDTO.getDoctorType(), recordDTO.getDate(), recordDTO.getTime(),
                    RecordStatus.WAITING.toString(), id);
            receptionDAO.addReception(jdbcTemplate.query(GET_RECORD_BY_CLIENT_ID_DATE_TIME_QUERY, new Object[]{id, recordDTO.getDate(),
                    recordDTO.getTime()}, new BeanPropertyRowMapper<>(Record.class)).get(0).getIdRecord());
        }
    }

    @Override
    public void deleteRecord(long id) {
        Record record = getRecordById(id);
        if (record.getStatus().equals(RecordStatus.DONE.toString())) {
            throw new DAORecordException(ExceptionCode.NOT_DELETING_RECORD.getErrorCode());
        } else if (record.getStatus().equals(RecordStatus.CONFIRMED.toString())) {
            throw new DAORecordException(ExceptionCode.NOT_DELETING_RECORD_1.getErrorCode());
        }
        jdbcTemplate.update(UPDATE_RECORD_STATUS_QUERY, RecordStatus.CANCELLED.toString(), id);
    }

    @Override
    public void updateRecord(long id, RecordDTO recordDTO) {
        Record record = getRecordById(id);
        if (record.getStatus().equals(RecordStatus.WAITING.toString())) {
            jdbcTemplate.update(UPDATE_RECORD_QUERY, recordDTO.getDoctorType(), recordDTO.getDate(),
                    recordDTO.getTime(), id);
        } else {
            throw new DAORecordException(ExceptionCode.NOT_UPDATING_RECORD.getErrorCode());
        }
    }

    @Override
    public List<RecordDTO> getAllRecords() {
        return jdbcTemplate.query(GET_ALL_RECORDS_QUERY, new BeanPropertyRowMapper<>(RecordDTO.class));
    }

    @Override
    public Record getRecordById(long id) {
        List<Record> records = jdbcTemplate.query(GET_RECORD_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(Record.class));
        if (records.isEmpty()) {
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_RECORD.getErrorCode());
        }
        return records.get(0);
    }

    @Override
    public List<RecordDTO> getRecordByClientId(long id) {
        List<RecordDTO> records = jdbcTemplate.query(GET_RECORD_BY_CLIENT_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(RecordDTO.class));
        if (records.isEmpty()) {
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_RECORD.getErrorCode());
        }
        return records;
    }

    @Override
    public List<RecordDTO> getRecordByDoctorId(long id) {
        List<RecordDTO> records = jdbcTemplate.query(GET_RECORD_BY_DOCTOR_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(RecordDTO.class));
        if (records.isEmpty()) {
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_RECORD.getErrorCode());
        }
        return records;
    }

    @Override
    public List<Record> getRecordByStatus(String status) {
        return jdbcTemplate.query(GET_RECORD_BY_STATUS_QUERY, new Object[]{status}, new BeanPropertyRowMapper<>(Record.class));
    }
}
