package by.overone.clinic.dao.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.*;
import by.overone.clinic.dao.exception.DAONotExistException;
import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.dto.DoctorTimetableDTO;
import by.overone.clinic.model.*;
import by.overone.clinic.util.constant.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * Class implementing the ReceptionDao interface
 * @see ReceptionDAO
 */
@Slf4j
@EnableScheduling
@Repository
@RequiredArgsConstructor
public class ReceptionDAOImpl implements ReceptionDAO {

    public final JdbcTemplate jdbcTemplate;

    public final DetailsDAO detailsDAO;

    public static final String UPDATE_RECORD_QUERY = "UPDATE " + RecordConstant.TABLE_RECORD + " SET " +
            RecordConstant.DOCTOR_ID + "=?, " + UserConstant.STATUS + "=? WHERE " + RecordConstant.RECORD_ID + "=?";

    private static final String ADD_CLIENT_RECORD_QUERY = "INSERT INTO " + ClientRecordConstant.TABLE_CLIENT_RECORD +
            " VALUES(0,?,?,?,?,?)";

    private static final String UPDATE_RECEPTION_QUERY = "UPDATE " + ReceptionConstant.TABLE_RECEPTION + " SET " +
            ReceptionConstant.DOCTOR_ID + "=? WHERE " + ReceptionConstant.RECEPTION_ID + "=?";

    private final static String ADD_RECEPTION_QUERY = "INSERT INTO " + ReceptionConstant.TABLE_RECEPTION +
            " VALUES(0,?,?,0)";

    private final static String GET_RECEPTION_BY_ID_QUERY = "SELECT * FROM " + ReceptionConstant.TABLE_RECEPTION +
            " WHERE " + ReceptionConstant.RECEPTION_ID + "=?";

    private final static String GET_BUSY_TIME_DOCTOR_TIMETABLE_QUERY = "SELECT * FROM " + DoctorTimetableConstant.TABLE_TIMETABLE +
            " JOIN " + DetailsConstant.TABLE_DOCTOR + " ON " + DoctorTimetableConstant.DOCTOR_ID +
            "=" + DetailsConstant.DOCTOR_ID + " WHERE " + DoctorTimetableConstant.DOCTOR_ID + "=? AND " + RecordConstant.DATE + "=?";

    private final String GET_BUSY_TIME_RECORDS_QUERY = "SELECT * FROM " + RecordConstant.TABLE_RECORD +
            " WHERE " + RecordConstant.DOCTOR_TYPE + "=? AND " + RecordConstant.DATE + "=? AND " + UserConstant.STATUS +
            "='WAITING'";

    private final static String GET_ALL_DOCTOR_TIME_QUERY = "SELECT * FROM timetable";

    private final static String UPDATE_RECORD_DONE_QUERY = "UPDATE " + RecordConstant.TABLE_RECORD + " SET " + UserConstant.STATUS +
            "='DONE' WHERE " + UserConstant.STATUS + "='CONFIRMED' AND DATE(" + RecordConstant.DATE + ")<? AND TIME(" +
            RecordConstant.TIME + ")<?";

    private final static String GET = "SELECT * FROM " + RecordConstant.TABLE_RECORD +
            " WHERE " + UserConstant.STATUS + "='CONFIRMED' AND DATE(" + RecordConstant.DATE + ")<? AND TIME(" +
            RecordConstant.TIME + ")<?";

    private final static String UPDATE_STATUS_DOCTOR_TIMETABLE_QUERY = "UPDATE " + DoctorTimetableConstant.TABLE_TIMETABLE +
            " SET " + UserConstant.STATUS + "=? WHERE " + DoctorTimetableConstant.DOCTOR_ID + "=? AND " + RecordConstant.DATE +
            "=? AND " + RecordConstant.TIME + "=?";

    /**
     * This method is used to create reception for record with record id
     *
     * @param recordId record id
     */
    @Override
    public void addReception(long recordId) {
        RecordDAO recordDAO = new RecordDAOImpl(jdbcTemplate, new ReceptionDAOImpl(jdbcTemplate, new DetailsDAOImpl(jdbcTemplate)));

        Record record = recordDAO.getRecordById(recordId);
        log.info(String.valueOf(record.getUser_user_id()));
        jdbcTemplate.update(ADD_RECEPTION_QUERY, recordId, record.getUser_user_id());
    }

    /**
     * This method is used to get reception by id from database
     *
     * @param id reception id
     * @return reception
     */
    @Override
    public Reception getById(long id) {
        List<Reception> receptions = jdbcTemplate.query(GET_RECEPTION_BY_ID_QUERY, new Object[]{id},
                new BeanPropertyRowMapper<>(Reception.class));
        if (receptions.isEmpty()) {
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_RECEPTION.getErrorCode());
        }
        return receptions.get(0);
    }

    /**
     * This method is used to get doctor's free time on certain date
     *
     * @param doctorType doctor's type
     * @param date date
     * @return time list
     */
    @Override
    public List<Time> getDoctorFreeTime(String doctorType, LocalDate date) {
        List<DocTimetableDTO> busyInDT = jdbcTemplate.query(GET_BUSY_TIME_DOCTOR_TIMETABLE_QUERY,
                new Object[]{findDoctorId(doctorType), date}, new BeanPropertyRowMapper<>(DocTimetableDTO.class));
        List<Record> busyInRecords = jdbcTemplate.query(GET_BUSY_TIME_RECORDS_QUERY, new Object[]{doctorType, date},
                new BeanPropertyRowMapper<>(Record.class));
        List<DocTimetableDTO> all = jdbcTemplate.query(GET_ALL_DOCTOR_TIME_QUERY, new BeanPropertyRowMapper<>(DocTimetableDTO.class));
        List<Time> free = new ArrayList<>();
        List<Time> free1 = free;

        for (DocTimetableDTO t : all) {
            free1.add(t.getTime());
        }

        for (DocTimetableDTO t : all) {
            for (DocTimetableDTO d : busyInDT) {
                if (d.getTime().equals(t.getTime())) {
                    free1.remove(d.getTime());
                }
            }
            for (Record r : busyInRecords) {
                if (r.getTime().equals(t.getTime())) {
                    free1.remove(r.getTime());
                }
            }
        }

        if (busyInDT.isEmpty()) {
            return free;
        } else {
            return free1;
        }
    }

    /**
     * This method is used to find doctor id by doctor type from db
     *
     * @param type doctor's type
     * @return doctor id
     */
    @Override
    public long findDoctorId(String type) {
        List<DoctorDetails> doctors = detailsDAO.getDoctorDetailsByType(type);
        return doctors.get(0).getDoctor_user_id();
    }

    /**
     * This method is used to confirm record according to reception id where record is stored
     *
     * @param receptionId reception id
     */
    @Transactional
    @Override
    public void confirmRecord(long receptionId) {
        Reception reception = getById(receptionId);

        RecordDAO recordDAO = new RecordDAOImpl(jdbcTemplate, new ReceptionDAOImpl(jdbcTemplate, new DetailsDAOImpl(jdbcTemplate)));

        Record record = recordDAO.getRecordById(reception.getRecord_id_record());

        long idDoctor = findDoctorId(record.getDoctorType());
        long idClient = record.getUser_user_id();

        log.info(String.valueOf(record.getUser_user_id()));

        reception.setDoctorId(idDoctor);
        reception.setClientId(idClient);

        ClientDetails clientDetails = detailsDAO.getClientDetails(idClient);

        DoctorTimetableDTO doctorTimetableDTO = new DoctorTimetableDTO();
        doctorTimetableDTO.setClientSurname(clientDetails.getSurname());
        doctorTimetableDTO.setDate(record.getDate());
        doctorTimetableDTO.setTime(record.getTime());
        doctorTimetableDTO.setDoctorId(idDoctor);

        DoctorTimetableDAO doctorTimetableDAO = new DoctorTimetableDAOImpl(jdbcTemplate);

        doctorTimetableDAO.addToDoctorTimetable(doctorTimetableDTO);

        jdbcTemplate.update(UPDATE_RECORD_QUERY, idDoctor, RecordStatus.CONFIRMED.toString(), reception.getRecord_id_record());

        DoctorDetails doctorDetails = detailsDAO.getDoctorDetails(idDoctor);

        jdbcTemplate.update(ADD_CLIENT_RECORD_QUERY, doctorDetails.getSurname(), doctorDetails.getDoctorType(),
                record.getDate(), record.getTime(), idClient);

        jdbcTemplate.update(UPDATE_RECEPTION_QUERY, idDoctor, reception.getIdReception());
    }

    /**
     * This method is used to update record status from CONFIRMED to DONE every 15 min automatically
     */
    @Scheduled(fixedRate = 15 * 60 * 1000)
    @Transactional
    @Override
    public void updateRecordDone() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String date = timeStamp.substring(0, 11);
        String time = timeStamp.substring(11);

        List<Record> rr = jdbcTemplate.query(GET, new Object[]{date, time}, new BeanPropertyRowMapper<>(Record.class));
        log.info(rr.toString());

        jdbcTemplate.update(UPDATE_RECORD_DONE_QUERY, date, time);

        RecordDAO recordDAO = new RecordDAOImpl(jdbcTemplate, new ReceptionDAOImpl(jdbcTemplate, new DetailsDAOImpl(jdbcTemplate)));

        List<Record> records = recordDAO.getRecordByStatus("DONE");

        for (Record r : records) {
            jdbcTemplate.update(UPDATE_STATUS_DOCTOR_TIMETABLE_QUERY, RecordStatus.DONE.toString(), r.getDoctor_id(),
                    r.getDate(), r.getTime());
        }
    }
}
