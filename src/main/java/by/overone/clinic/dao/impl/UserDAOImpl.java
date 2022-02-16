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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class implementing the UserDao interface
 * @see UserDAO
 */
@Slf4j
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

    /**
     * This method is used to add users
     *
     * @param userRegistrationDTO data for registration
     * @see UserRegistrationDTO
     */
    @Override
    public void addUser(UserRegistrationDTO userRegistrationDTO) {
        jdbcTemplate.update(ADD_USER_QUERY, userRegistrationDTO.getLogin(), DigestUtils.md5Hex(userRegistrationDTO.getPassword()),
                userRegistrationDTO.getEmail(), Role.USER.toString(), Status.ACTIVE.toString());
    }

    /**
     * This method is used to delete user by his id
     *
     * @param id user id
     */
    @Override
    public void deleteUserById(long id) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
    }

    /**
     * This method is used to update user data
     *
     * @param id             user id
     * @param userUpdatedDTO data that user can update
     * @see UserUpdatedDTO
     */
    @Override
    public void updateUser(long id, UserUpdatedDTO userUpdatedDTO) {
        jdbcTemplate.update(UPDATE_USER_QUERY, userUpdatedDTO.getLogin(), DigestUtils.md5Hex(userUpdatedDTO.getPassword()),
                userUpdatedDTO.getEmail(), id);
    }

    /**
     * This method is used to get user by id
     *
     * @param id user id
     * @return user with needed id
     */
    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    /**
     * This method is used to return list of all users from database
     *
     * @return all users
     */
    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(GET_ALL_USERS_QUERY, new BeanPropertyRowMapper<>(User.class));
    }

    /**
     *This method returns all users depending on the parameters that are passed to search for users
     *
     * @param status status
     * @param role role
     * @param surname surname
     * @return all users matching the passed parameters
     */
    @Override
    public List<User> get(String status, String role, String surname) {
        StringBuffer sql = new StringBuffer("SELECT * FROM " + UserConstant.TABLE_USER);
        List<String> strings = new ArrayList<>();

        if (!surname.equals("")) {
            if (status.equals("") && role.equals("")) {
                sql.append(" JOIN " + DetailsConstant.TABLE_CLIENT + " ON " + UserConstant.USER_ID + "=" +
                        DetailsConstant.CLIENT_ID + " WHERE surname=? ");
            } else {
                sql.append(" JOIN " + DetailsConstant.TABLE_CLIENT + " ON " + UserConstant.USER_ID + "=" +
                        DetailsConstant.CLIENT_ID + " WHERE surname=? AND");
            }
            strings.add(surname);
        } else {
            sql.append(" WHERE");
        }
        if (!status.equals("")) {
            sql.append(" status=? ");
            if (!role.equals("")) {
                sql.append("AND ");
            }
            strings.add(status);
        }
        if (!role.equals("")) {
            sql.append(" role=? ");
            strings.add(role);
        }

        Object[] data = new String[strings.size()];

        for (int i = 0; i < strings.size(); i++) {
            data[i] = strings.get(i);
        }

        log.info(sql.toString());

        return jdbcTemplate.query(sql.toString(), data, new BeanPropertyRowMapper<>(User.class));
    }
}
