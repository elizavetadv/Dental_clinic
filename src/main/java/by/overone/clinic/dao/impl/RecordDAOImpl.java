package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.RecordDAO;
import by.overone.clinic.dto.RecordDTO;
import by.overone.clinic.dto.RecordUpdatedDTO;
import by.overone.clinic.model.Record;
import by.overone.clinic.model.RecordStatus;
import by.overone.clinic.util.constant.RecordConstant;
import by.overone.clinic.util.constant.UserConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordDAOImpl implements RecordDAO {

    public final JdbcTemplate jdbcTemplate;

    public static final String ADD_RECORD_QUERY = "INSERT INTO " + RecordConstant.TABLE_RECORD +
            " VALUES(0, ?, ?, ?, ?, ?, 0)";

    public static final String UPDATE_RECORD_STATUS_QUERY = "UPDATE " + RecordConstant.TABLE_RECORD + " SET " +
            UserConstant.STATUS + "=? WHERE " + RecordConstant.RECORD_ID + "=?";

    private final static String UPDATE_RECORD_QUERY = "UPDATE " + RecordConstant.TABLE_RECORD + " SET " +
            RecordConstant.DOCTOR_TYPE + "=COALESCE(?," + RecordConstant.DOCTOR_TYPE + "), " +
            RecordConstant.DATE + "=COALESCE(?," + RecordConstant.DATE + "), " +
            RecordConstant.TIME + "=COALESCE(?," + RecordConstant.TIME + ") " +
            "WHERE " + RecordConstant.RECORD_ID + "=?";

    private final static String GET_ALL_RECORDS_QUERY = "SELECT id_record, doctor_type, date, time, status," +
            "user_user_id, doctor_id FROM " + RecordConstant.TABLE_RECORD;

    private final static String GET_RECORD_BY_ID_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.RECORD_ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_CLIENT_ID_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.CLIENT_ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_DOCTOR_ID_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD + " WHERE " +
            RecordConstant.DOCTOR_ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    private final static String GET_RECORD_BY_STATUS_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD +
            " WHERE " + UserConstant.STATUS + "=?";

    @Override
    public void makeRecord(long id, RecordDTO recordDTO) {
        jdbcTemplate.update(ADD_RECORD_QUERY, recordDTO.getDoctorType(), recordDTO.getDate(), recordDTO.getTime(), RecordStatus.WAITING.toString(), id);
    }

    @Override
    public void deleteRecord(long id) {
        jdbcTemplate.update(UPDATE_RECORD_STATUS_QUERY, RecordStatus.CANCELLED.toString(), id);
    }

    @Override
    public void updateRecord(RecordUpdatedDTO recordUpdatedDTO) {
        jdbcTemplate.update(UPDATE_RECORD_QUERY, recordUpdatedDTO.getDoctorType(), recordUpdatedDTO.getDate(),
                recordUpdatedDTO.getTime(), recordUpdatedDTO.getId_record());
    }

    @Override
    public List<Record> getAllRecords() {
        return jdbcTemplate.query(GET_ALL_RECORDS_QUERY, new BeanPropertyRowMapper<>(Record.class));
    }

    @Override
    public Record getRecordById(long id) {
        return jdbcTemplate.query(GET_RECORD_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(Record.class)).get(0);
    }

    @Override
    public Record getRecordByClientId(long id) {
        return jdbcTemplate.query(GET_RECORD_BY_CLIENT_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(Record.class)).get(0);
    }

    @Override
    public Record getRecordByDoctorId(long id) {
        return jdbcTemplate.query(GET_RECORD_BY_DOCTOR_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(Record.class)).get(0);
    }

    @Override
    public List<Record> getRecordByStatus(String status) {
        return jdbcTemplate.query(GET_RECORD_BY_STATUS_QUERY, new Object[]{status}, new BeanPropertyRowMapper<>(Record.class));
    }
}
