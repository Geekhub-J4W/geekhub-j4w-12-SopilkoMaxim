package edu.geekhub.bucket;

import edu.geekhub.customer.Customer;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public static final String GET_ORDER_BY_ID = """
            SELECT * FROM order WHERE id = 
            """;
    private static final String INSERT_ORDER = """
            INSERT INTO order (quantiti_products,order_date,id_customer,id_product) VALUES (:quantiti_products,:order_date,:id_customer,:id_product)
            """;

    public static final String DELETE_BY_ID = """
            DELETE FROM order WHERE id = :id
            """;
    public static final String FETCH_ALL_ORDERS = """
            SELECT * FROM order
            """;
    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addOrder(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("quantity_products", order.getQuantity_products())
                .addValue("order_date", order.getDate())
                .addValue("id_customer",order.getId_customer())
                .addValue("id_product",order.getId_product());
        jdbcTemplate.update(INSERT_ORDER, parameters);
    }

    public Order getOrderById(int id) {
        String getOrder = GET_ORDER_BY_ID + id;
        List<Order> result = jdbcTemplate.query(getOrder, (rs, rowNum) -> new Order(
                rs.getInt("id"),
                rs.getInt("quantity_products"),
                rs.getDate("order_date"),
                rs.getInt("id_customer"),
                rs.getInt("id_product")));
        return result.get(0);
    }

    public void deleteOrder(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID, parameters);
    }

    public List<Order> getOrders() {
        return jdbcTemplate.query(FETCH_ALL_ORDERS, (rs, rowNum) -> new Order(
                rs.getInt("id"),
                rs.getInt("quantity_products"),
                rs.getDate("order_date"),
                rs.getInt("id_customer"),
                rs.getInt("id_product")));
    }

    public void update(int id, Order order) {
        String updateQuery="update order set quantity_products = :quantity_products, order_date = :order_date," +
                "id_customer = :id_customer, id_product = :id_product where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("quantity_products",order.getQuantity_products())
                .addValue("order_date",order.getDate())
                .addValue("id_customer",order.getId_customer())
                .addValue("id_product",order.getId_product());

        jdbcTemplate.update(updateQuery,parameters);
    }

}
