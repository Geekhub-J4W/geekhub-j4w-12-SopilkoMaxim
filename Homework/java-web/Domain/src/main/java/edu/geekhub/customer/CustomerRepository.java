package edu.geekhub.customer;

import edu.geekhub.product.Product;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_CUSTOMER = """
            INSERT INTO customer (name,age) VALUES (:name,:age)
            """;

    public static final String FETCH_ALL_CUSTOMERS = """
            SELECT * FROM customer
            """;

    public static final String GET_USER_BY_ID = """
            SELECT * FROM customer WHERE id = 
            """;
    public static final String DELETE_BY_ID = """
            DELETE FROM customer WHERE id = :id
            """;
    private final List<Customer> customers = new ArrayList<>();

    public CustomerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCustomer(Customer customer) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", customer.getName())
                .addValue("age", customer.getAge());
        jdbcTemplate.update(INSERT_CUSTOMER, parameters);
    }

    public Customer getCustomerById(int id) {
        String getCust = GET_USER_BY_ID + id;
        List<Customer> result = jdbcTemplate.query(getCust, (rs, rowNum) -> new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age")));
        return result.get(0);
    }

    public void deleteCustomer(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID, parameters);
    }

    public List<Customer> getCustomers() {
        return jdbcTemplate.query(FETCH_ALL_CUSTOMERS, (rs, rowNum) -> new Customer(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age")
        ));
    }

    public void update(int id, Customer customer) {
        String updateQuery="update customer set name = :name, age = :age where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("name",customer.getName())
                .addValue("age",customer.getAge());

        jdbcTemplate.update(updateQuery,parameters);
    }
}
