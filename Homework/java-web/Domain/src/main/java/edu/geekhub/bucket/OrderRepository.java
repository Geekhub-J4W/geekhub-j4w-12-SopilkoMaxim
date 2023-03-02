package edu.geekhub.bucket;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_ORDER = """
            INSERT INTO order (quantiti_products,order_date,id_customer,id_product) VALUES (:quantiti_products,:order_date,:id_customer,:id_product)
            """;

    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addOrder(Order order) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("quantiti_products", order.getQuantity_products())
                .addValue("order_date", order.getDate())
                .addValue("id_customer",order.getId_customer())
                .addValue("id_product",order.getId_product());
        jdbcTemplate.update(INSERT_ORDER, parameters);
    }

}
