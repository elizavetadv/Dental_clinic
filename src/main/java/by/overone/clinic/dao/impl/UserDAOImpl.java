package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.dto.UserRegistrationDTO;
import by.overone.clinic.dto.UserUpdatedDTO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.User;
import by.overone.clinic.util.constant.DetailsConstant;
import by.overone.clinic.util.constant.UserConstant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    public final JdbcTemplate jdbcTemplate;

    private final static String ADD_USER_QUERY = "INSERT INTO " + UserConstant.TABLE_USER + " VALUE (0, ?, ?, ?, ?, ?)";

    private final static String DELETE_USER_BY_ID_QUERY = "UPDATE " + UserConstant.TABLE_USER + " SET " +
            UserConstant.STATUS + "='DELETED' WHERE " + UserConstant.USER_ID + "=?";

    private final static String UPDATE_USER_QUERY = "UPDATE " + UserConstant.TABLE_USER + " SET " +
            UserConstant.LOGIN + "=COALESCE(?," + UserConstant.LOGIN + "), " +
            UserConstant.PASSWORD + "=COALESCE(?," + UserConstant.PASSWORD + "), " +
            UserConstant.EMAIL + "=COALESCE(?," + UserConstant.EMAIL + ")" +
            "WHERE " + UserConstant.USER_ID + "=?";

    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM " + UserConstant.TABLE_USER + " WHERE " +
            UserConstant.USER_ID + "=? AND " + UserConstant.STATUS + "='ACTIVE'";

    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM " + UserConstant.TABLE_USER;

    private final static String GET_ALL_USERS_BY_STATUS_QUERY = "SELECT * FROM " + UserConstant.TABLE_USER +
            " WHERE " + UserConstant.STATUS + "=?";

    private final static String GET_ALL_USERS_BY_ROLE_QUERY = "SELECT * FROM " + UserConstant.TABLE_USER +
            " WHERE " + UserConstant.ROLE + "=? AND " + UserConstant.STATUS + "='ACTIVE'";

    private final static String GET_USERS_BY_SURNAME_QUERY = "SELECT * FROM " + UserConstant.TABLE_USER +
            " LEFT JOIN " + DetailsConstant.TABLE_CLIENT + " ON " + DetailsConstant.CLIENT_ID + "=" + UserConstant.USER_ID +
            " LEFT JOIN " + DetailsConstant.TABLE_DOCTOR + " ON " + DetailsConstant.DOCTOR_ID + "=" + UserConstant.USER_ID +
            " WHERE status='ACTIVE' AND " + "doctor_details.surname=? OR client_details.surname=?";

    @Override
    public void addUser(UserRegistrationDTO userRegistrationDTO) {
        jdbcTemplate.update(ADD_USER_QUERY, userRegistrationDTO.getLogin(), DigestUtils.md5Hex(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getEmail(), Role.USER.toString(), Status.ACTIVE.toString());
    }

    @Override
    public void deleteUserById(long id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
    }

    @Override
    public void updateUser(long id, UserUpdatedDTO userUpdatedDTO) {
        jdbcTemplate.update(UPDATE_USER_QUERY, userUpdatedDTO.getLogin(), DigestUtils.md5Hex(userUpdatedDTO.getPassword()),
                userUpdatedDTO.getEmail(), id);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(GET_ALL_USERS_QUERY, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getAllUsersByStatus(String status) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_STATUS_QUERY, new Object[]{status}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getAllUsersByRole(String role) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_ROLE_QUERY, new Object[]{role}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getUserBySurname(String surname) {
        return jdbcTemplate.query(GET_USERS_BY_SURNAME_QUERY, new Object[]{surname, surname}, new BeanPropertyRowMapper<>(User.class));
    }
}
