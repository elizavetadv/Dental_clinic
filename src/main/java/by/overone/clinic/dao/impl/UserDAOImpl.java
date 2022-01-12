package by.overone.clinic.dao.impl;

import by.overone.clinic.dao.UserDAO;
import by.overone.clinic.model.Role;
import by.overone.clinic.model.Status;
import by.overone.clinic.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    public final JdbcTemplate jdbcTemplate;

    private final static String ADD_USER_QUERY = "INSERT INTO user VALUE (0, ?, ?, ?, ?, ?);";
    private final static String DELETE_USER_BY_ID_QUERY = "UPDATE user SET status='DELETED' WHERE user_id=?;";

    private final static String GET_USER_BY_ID_QUERY = "SELECT * FROM user WHERE user_id=? AND status='ACTIVE';";
    private final static String GET_USER_QUERY = "SELECT * FROM user WHERE user_id=?;";
    private final static String GET_ALL_USERS_QUERY = "SELECT * FROM user;";
    private final static String GET_ALL_USERS_BY_STATUS_QUERY = "SELECT * FROM user WHERE status=?;";
    private final static String GET_ALL_USERS_BY_ROLE_QUERY = "SELECT * FROM user WHERE role=? AND status='ACTIVE';";

    private final static String GET_USER_BY_SURNAME_QUERY = "SELECT * FROM user WHERE surname=? AND status='ACTIVE';";

    @Override
    public void addUser(User user) {
        jdbcTemplate.update(ADD_USER_QUERY, user.getLogin(),user.getPassword(), user.getEmail(), Role.USER.toString(),
                Status.ACTIVE.toString());
    }

    @Override
    public void deleteUserById(long id){
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);
    }

    @Override
    public Optional<User> getUserById(long id) {
        return jdbcTemplate.query(GET_USER_BY_ID_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

    @Override
    public User getUser(long id) {
        return jdbcTemplate.query(GET_USER_QUERY, new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).get(0);
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

    //???
    @Override
    public Optional<User> getUserBySurname(String surname) {
        return jdbcTemplate.query(GET_USER_BY_SURNAME_QUERY, new Object[]{surname}, new BeanPropertyRowMapper<>(User.class)).stream().findAny();
    }

}
