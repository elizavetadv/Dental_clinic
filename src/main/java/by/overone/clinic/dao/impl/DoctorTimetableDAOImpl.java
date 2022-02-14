package by.overone.clinic.dao.impl;

import by.overone.clinic.controller.exception.ExceptionCode;
import by.overone.clinic.dao.DoctorTimetableDAO;
import by.overone.clinic.dao.exception.DAOIncorrectDataException;
import by.overone.clinic.dao.exception.DAONotExistException;
import by.overone.clinic.dto.DocTimetableDTO;
import by.overone.clinic.dto.DoctorTimetableDTO;
import by.overone.clinic.model.RecordStatus;
import by.overone.clinic.util.constant.DoctorTimetableConstant;
import by.overone.clinic.util.constant.UserConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DoctorTimetableDAOImpl implements DoctorTimetableDAO {

    public final JdbcTemplate jdbcTemplate;

    public static final String ADD_TO_DOCTOR_TIMETABLE_QUERY = "INSERT INTO " + DoctorTimetableConstant.TABLE_TIMETABLE +
            " VALUES(0, ?, ?, ?, ?, ?)";

    private final static String GET_ALL_RECORDS_BY_DOCTOR_ID_QUERY = "SELECT * FROM " + DoctorTimetableConstant.TABLE_TIMETABLE +
            " WHERE " + DoctorTimetableConstant.DOCTOR_ID + "=?";

    private final static String GET_RECORD_BY_ID_QUERY = "SELECT * FROM " + DoctorTimetableConstant.TABLE_TIMETABLE + " WHERE " +
            DoctorTimetableConstant.ID + "=? AND " + UserConstant.STATUS + "<>'CANCELLED'";

    @Override
    public void addToDoctorTimetable(DoctorTimetableDTO doctorTimetableDTO) {
        jdbcTemplate.update(ADD_TO_DOCTOR_TIMETABLE_QUERY, doctorTimetableDTO.getClientSurname(), doctorTimetableDTO.getDate(),
                doctorTimetableDTO.getTime(), doctorTimetableDTO.getDoctorId(), RecordStatus.CONFIRMED.toString());
    }

    @Override
    public DocTimetableDTO getRecordById(long id) {
        List<DocTimetableDTO> records = jdbcTemplate.query(GET_RECORD_BY_ID_QUERY, new Object[]{id},
                new BeanPropertyRowMapper<>(DocTimetableDTO.class));
        if (records.isEmpty()) {
            throw new DAONotExistException(ExceptionCode.NOT_EXISTING_RECORD.getErrorCode());
        }
        return records.get(0);
    }

    @Override
    public List<DocTimetableDTO> getRecordByDate(int id, int day, int month, int year) {
        StringBuffer sql = new StringBuffer("SELECT * FROM " + DoctorTimetableConstant.TABLE_TIMETABLE + " WHERE ");
        List<Integer> num = new ArrayList<>();

        if((day < 0 || day > 31) || (month < 0 || month > 12) || (year != 0 && year <= 2021)){
            throw new DAOIncorrectDataException(ExceptionCode.INCORRECT_QUERY_DATA.getErrorCode());
        }

        if (day != 0) {
            sql.append("EXTRACT(DAY FROM date) =? AND ");
            num.add(day);
        }
        if (month != 0) {
            sql.append("EXTRACT(MONTH FROM date) =? AND ");
            num.add(month);
        }
        if (year != 0) {
            num.add(year);
            sql.append("EXTRACT(YEAR FROM date) =? AND ");
        }

        sql.append(DoctorTimetableConstant.DOCTOR_ID + "=?");

        Object[] date = new Object[num.size() + 1];
        for(int i = 0; i < num.size(); i++){
            date[i] = num.get(i);
        }

        date[num.size()] = id;

        return jdbcTemplate.query(sql.toString(), date, new BeanPropertyRowMapper<>(DocTimetableDTO.class));
    }

    @Override
    public List<DocTimetableDTO> getAllByDoctorId(long id) {
        return jdbcTemplate.query(GET_ALL_RECORDS_BY_DOCTOR_ID_QUERY, new Object[]{id},
                new BeanPropertyRowMapper<>(DocTimetableDTO.class));
    }
}
