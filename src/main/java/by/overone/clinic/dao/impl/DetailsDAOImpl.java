package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dto.*;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.model.Role;
import by.overone.clinic.util.constant.ClientRecordConstant;
import by.overone.clinic.util.constant.DetailsConstant;
import by.overone.clinic.util.constant.UserConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DetailsDAOImpl implements DetailsDAO {

    public final JdbcTemplate jdbcTemplate;

    public static final String ADD_CLIENT_DETAILS_QUERY = "INSERT INTO " + DetailsConstant.TABLE_CLIENT +
            " VALUES(?, ?, ?, ?, ?, ?)";

    public static final String ADD_DOCTOR_DETAILS_QUERY = "INSERT INTO " + DetailsConstant.TABLE_DOCTOR +
            " VALUES(?, ?, ?, ?)";

    public static final String UPDATE_USER_ROLE_QUERY = "UPDATE " + UserConstant.TABLE_USER + " SET " +
            UserConstant.ROLE + "=? WHERE " + UserConstant.USER_ID + "=?";

    public static final String UPDATE_CLIENT_DETAILS_QUERY = "UPDATE " + DetailsConstant.TABLE_CLIENT + " SET " +
            DetailsConstant.NAME + "=COALESCE(?," + DetailsConstant.NAME + "), " +
            DetailsConstant.SURNAME + "=COALESCE(?," + DetailsConstant.SURNAME + "), " +
            DetailsConstant.ADDRESS + "=COALESCE(?," + DetailsConstant.ADDRESS + "), " +
            DetailsConstant.DATA_BIRTH + "=COALESCE(?," + DetailsConstant.DATA_BIRTH + "), " +
            DetailsConstant.PHONE_NUMBER + "=COALESCE(?," + DetailsConstant.PHONE_NUMBER + ")" +
            " WHERE " + DetailsConstant.CLIENT_ID + "=?";

    public static final String UPDATE_DOCTOR_DETAILS_QUERY = "UPDATE " + DetailsConstant.TABLE_DOCTOR + " SET " +
            DetailsConstant.NAME + "=COALESCE(?," + DetailsConstant.NAME + "), " +
            DetailsConstant.SURNAME + "=COALESCE(?," + DetailsConstant.SURNAME + "), " +
            DetailsConstant.DOCTOR_TYPE + "=COALESCE(?," + DetailsConstant.DOCTOR_TYPE + ")" +
            " WHERE " + DetailsConstant.DOCTOR_ID + "=?";

    private final static String GET_CLIENT_DETAILS_QUERY = "SELECT * FROM " + DetailsConstant.TABLE_CLIENT +
            " WHERE " + DetailsConstant.CLIENT_ID + "=?";

    private final static String GET_DOCTOR_DETAILS_QUERY = "SELECT * FROM " + DetailsConstant.TABLE_DOCTOR +
            " WHERE " + DetailsConstant.DOCTOR_ID + "=?";

    private final static String GET_DOCTOR_DETAILS_BY_TYPE_QUERY = "SELECT * FROM " + DetailsConstant.TABLE_DOCTOR +
            " WHERE " + DetailsConstant.DOCTOR_TYPE + "=?";

    private final static String GET_ALL_CLIENT_DATA_QUERY = "SELECT " + UserConstant.USER_ID + ", " + UserConstant.LOGIN +
            ", " + UserConstant.EMAIL + ", " + DetailsConstant.NAME + ", " + DetailsConstant.SURNAME + ", " +
            DetailsConstant.ADDRESS + ", " + DetailsConstant.DATA_BIRTH + ", " + DetailsConstant.PHONE_NUMBER +
            " FROM " + UserConstant.TABLE_USER + " JOIN " + DetailsConstant.TABLE_CLIENT + " ON " +
            UserConstant.TABLE_USER + "." + UserConstant.USER_ID + "=" + DetailsConstant.TABLE_CLIENT + "." +
            DetailsConstant.CLIENT_ID + " WHERE " + UserConstant.STATUS + "='ACTIVE' AND " + UserConstant.USER_ID + "=?";

    private final static String GET_ALL_DOCTOR_DATA_QUERY = "SELECT " + UserConstant.USER_ID + ", " + UserConstant.LOGIN +
            ", " + UserConstant.EMAIL + ", " + DetailsConstant.NAME + ", " + DetailsConstant.SURNAME + ", " +
            DetailsConstant.DOCTOR_TYPE + " FROM " + UserConstant.TABLE_USER + " JOIN " + DetailsConstant.TABLE_DOCTOR +
            " ON " + UserConstant.TABLE_USER + "." + UserConstant.USER_ID + "=" + DetailsConstant.TABLE_DOCTOR + "." +
            DetailsConstant.DOCTOR_ID + " WHERE " + UserConstant.STATUS + "='ACTIVE' AND " + UserConstant.USER_ID + "=?";

    private final static String GET_CLIENT_RECORDS_QUERY = "SELECT * FROM " + ClientRecordConstant.TABLE_CLIENT_RECORD +
            " WHERE " + ClientRecordConstant.CLIENT_ID + "=?";

    @Transactional
    @Override
    public void addClientDetails(long id, ClientDetailsDTO clientDetailsDTO) {
        jdbcTemplate.update(ADD_CLIENT_DETAILS_QUERY, id, clientDetailsDTO.getName(), clientDetailsDTO.getSurname(),
                clientDetailsDTO.getAddress(), clientDetailsDTO.getDataBirth(), clientDetailsDTO.getPhoneNumber());

        jdbcTemplate.update(UPDATE_USER_ROLE_QUERY, Role.CLIENT.toString(), id);
    }

    @Transactional
    @Override
    public void addDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO) {
        jdbcTemplate.update(ADD_DOCTOR_DETAILS_QUERY, id, doctorDetailsDTO.getName(), doctorDetailsDTO.getSurname(),
                doctorDetailsDTO.getDoctorType());

        jdbcTemplate.update(UPDATE_USER_ROLE_QUERY, Role.DOCTOR.toString(), id);
    }

    @Override
    public void updateClientDetails(long id, ClientDetailsDTO clientDetailsDTO) {
        jdbcTemplate.update(UPDATE_CLIENT_DETAILS_QUERY, clientDetailsDTO.getName(), clientDetailsDTO.getSurname(),
                clientDetailsDTO.getAddress(), clientDetailsDTO.getDataBirth(), clientDetailsDTO.getPhoneNumber(), id);
    }

    @Override
    public void updateDoctorDetails(long id, DoctorDetailsDTO doctorDetailsDTO) {
        jdbcTemplate.update(UPDATE_DOCTOR_DETAILS_QUERY, doctorDetailsDTO.getName(), doctorDetailsDTO.getSurname(),
                doctorDetailsDTO.getDoctorType(), id);
    }

    @Override
    public ClientDetails getClientDetails(long id) {
        return jdbcTemplate.query(GET_CLIENT_DETAILS_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(ClientDetails.class)).get(0);
    }

    @Override
    public DoctorDetails getDoctorDetails(long id) {
        return jdbcTemplate.query(GET_DOCTOR_DETAILS_QUERY, new Object[]{id},
                new BeanPropertyRowMapper<>(DoctorDetails.class)).get(0);
    }

    @Override
    public List<DoctorDetails> getDoctorDetailsByType(String type) {
        return jdbcTemplate.query(GET_DOCTOR_DETAILS_BY_TYPE_QUERY, new Object[]{type.toUpperCase()},
                new BeanPropertyRowMapper<>(DoctorDetails.class));
    }

    @Override
    public ClientAllDataDTO getAllClientData(long id) {
        return jdbcTemplate.query(GET_ALL_CLIENT_DATA_QUERY,  new Object[]{id}, new BeanPropertyRowMapper<>(ClientAllDataDTO.class)).get(0);
    }

    @Override
    public DoctorAllDataDTO getAllDoctorData(long id) {
        return jdbcTemplate.query(GET_ALL_DOCTOR_DATA_QUERY,  new Object[]{id}, new BeanPropertyRowMapper<>(DoctorAllDataDTO.class)).get(0);
    }

    @Override
    public List<ClientRecordDTO> getClientRecord(long id) {
        return jdbcTemplate.query(GET_CLIENT_RECORDS_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(ClientRecordDTO.class));
    }
}
