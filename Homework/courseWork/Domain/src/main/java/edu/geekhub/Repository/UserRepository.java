package edu.geekhub.Repository;

import edu.geekhub.Entity.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_USER = """
            INSERT INTO user (name,age,email,password,balance) VALUES (:name,:age,:email,:password,:balance)
            """;

    public static final String FETCH_ALL_USERS = """
            SELECT * FROM user
            """;

    public static final String GET_USER_BY_ID = """
            SELECT * FROM user WHERE id = 
            """;
    public static final String DELETE_BY_ID = """
            DELETE FROM user WHERE id = :id
            """;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("balance", user.getBalance());
        jdbcTemplate.update(INSERT_USER, parameters);
    }

    public User getUserById(int id) {
        String getUser = GET_USER_BY_ID + id;
        List<User> result = jdbcTemplate.query(getUser, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getFloat("balance")));
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
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getFloat("balance")));
    }

    public void update(int id, User user) {
        String updateQuery="update user set name = :name, age = :age,email = :email, password = :password, balance = :balance where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("name",user.getName())
                .addValue("age",user.getAge())
                .addValue("email",user.getEmail())
                .addValue("password",user.getPassword())
                .addValue("balance",user.getBalance());

        jdbcTemplate.update(updateQuery,parameters);
    }
}
