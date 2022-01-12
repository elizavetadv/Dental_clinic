package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.DBConnect;
import by.overone.clinic.dao.DetailsDAO;
import by.overone.clinic.dao.exception.DAOException;
import by.overone.clinic.model.ClientDetails;
import by.overone.clinic.model.DoctorDetails;
import by.overone.clinic.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class DetailsDAOImpl implements DetailsDAO {

    public final JdbcTemplate jdbcTemplate;

    public static final String ADD_CLIENT_DETAILS_QUERY = "INSERT INTO client_details VALUES(?, ?, ?, ?, ?, ?);";
    public static final String ADD_DOCTOR_DETAILS_QUERY = "INSERT INTO doctor_details VALUES(?, ?, ?, ?);";

    public static final String UPDATE_USER_ROLE_QUERY = "UPDATE user SET role=? WHERE user_id=?";
    public static final String UPDATE_CLIENT_DETAILS_QUERY = "UPDATE client_details SET name=?, surname=?, address=?, " +
            "data_birth=?, phone_number=? WHERE name=? AND surname=?";
    public static final String UPDATE_DOCTOR_DETAILS_QUERY = "UPDATE doctor_details SET name=?, surname=?," +
            "doctor_type=? WHERE doctor_user_id=?";

    private final static String GET_CLIENT_DETAILS_QUERY = "SELECT * FROM client_details WHERE client_user_id=?;";
    private final static String GET_DOCTOR_DETAILS_QUERY = "SELECT * FROM doctor_details WHERE doctor_user_id=?;";

    @Transactional
    @Override
    public void addClientDetails(long id, ClientDetails userDetails) {
        jdbcTemplate.update(ADD_CLIENT_DETAILS_QUERY, id, userDetails.getName(), userDetails.getSurname(),userDetails.getAddress(),
                userDetails.getDataBirth(), userDetails.getPhoneNumber());

        jdbcTemplate.update(UPDATE_USER_ROLE_QUERY, Role.CLIENT.toString(), id);
    }

    @Transactional
    @Override
    public void addDoctorDetails(long id, DoctorDetails doctorDetails) {
        jdbcTemplate.update(ADD_DOCTOR_DETAILS_QUERY, id, doctorDetails.getName(), doctorDetails.getSurname(),
                doctorDetails.getDoctorType());

        jdbcTemplate.update(UPDATE_USER_ROLE_QUERY, Role.DOCTOR.toString(), id);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) {
        jdbcTemplate.update(UPDATE_CLIENT_DETAILS_QUERY, clientDetails.getName(), clientDetails.getSurname(),
                clientDetails.getAddress(), clientDetails.getDataBirth(), clientDetails.getPhoneNumber(), clientDetails.getUser_id());
    }

    @Override
    public void updateDoctorDetails(DoctorDetails doctorDetails) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_DOCTOR_DETAILS_QUERY);

            preparedStatement.setString(1, doctorDetails.getName());
            preparedStatement.setString(2, doctorDetails.getSurname());
            preparedStatement.setString(3, doctorDetails.getDoctorType());
            preparedStatement.setLong(4, doctorDetails.getDoctor_id());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException("DoctorDetails wasn't updated");
        }
    }

    @Override
    public ClientDetails getClientDetails(long id) {
        ClientDetails clientDetails = jdbcTemplate.query(GET_CLIENT_DETAILS_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(ClientDetails.class)).get(0);
        clientDetails.setUser_id(id);
        return clientDetails;
    }

    @Override
    public DoctorDetails getDoctorDetails(long id) {
        DoctorDetails doctorDetails = jdbcTemplate.query(GET_DOCTOR_DETAILS_QUERY, new Object[]{id},
                new BeanPropertyRowMapper<>(DoctorDetails.class)).get(0);
        doctorDetails.setDoctor_id(id);
        return doctorDetails;
    }
}
