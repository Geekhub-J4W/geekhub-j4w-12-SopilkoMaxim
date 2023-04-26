package edu.geekhub.user;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_USER = """
            INSERT INTO userdb (email,password,fullName,role,status) VALUES (:email,:password,:fullName,:role,:status)
            """;

    public static final String FETCH_ALL_USERS = """
            SELECT  id, email, password, fullName, role, status FROM userdb
            """;

    public static final String GET_USER_BY_ID = """
            SELECT * FROM userdb WHERE id = 
            """;
    public static final String GET_USER_BY_EMAIL = """
            SELECT * FROM userdb WHERE email = '%s'
            """;
    public static final String DELETE_BY_ID = """
            DELETE FROM userdb WHERE id = :id
            """;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("fullName",user.getFullName())
                .addValue("role",user.getRole().toString())
                .addValue("status",user.getStatus().toString());
        jdbcTemplate.update(INSERT_USER, parameters);
    }

    public User getUserById(int id) {
        String getUser = GET_USER_BY_ID + id;
        List<User> result = jdbcTemplate.query(getUser, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("role"),
                rs.getString("status")));
        return result.get(0);
    }

    public User getUserByEmail(String email) {
        String getUser = String.format(GET_USER_BY_EMAIL, email);
        List<User> result = jdbcTemplate.query(getUser, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("role"),
                rs.getString("status")));
        return result.get(0);
    }

    public void deleteUser(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID, parameters);
    }

    public List<User> getUsers() {
        return jdbcTemplate.query(FETCH_ALL_USERS, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("fullName"),
                rs.getString("role"),
                rs.getString("status")));
    }

    public void update(int id, User user) {
        String updateQuery="update user set email = :email, password = :password,"+
                " fullName = :fullname, role = :role, status = :status where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("email",user.getEmail())
                .addValue("fullname",user.getFullName())
                .addValue("role",user.getRole().toString())
                .addValue("status",user.getStatus().toString());

        jdbcTemplate.update(updateQuery,parameters);
    }
}
