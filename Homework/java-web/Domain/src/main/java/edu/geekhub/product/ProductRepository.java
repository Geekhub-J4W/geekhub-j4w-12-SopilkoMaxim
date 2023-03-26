package edu.geekhub.product;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.List;


@Component
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_PRODUCT = """
            INSERT INTO product (name,price,quantity,rating,imgbytes) VALUES (:name,:price,:quantity,:rating,:imgbytes)
            """;

    public static final String FETCH_ALL_PRODUCTS = """
            SELECT * FROM product
            """;

    public static final String GET_PRODUCT_BY_ID = """
            SELECT * FROM product WHERE id = 
            """;
    public static final String DELETE_BY_ID = """
            DELETE FROM product WHERE id = :id
            """;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;

    }

    public void addProduct(Product product) {
        MapSqlParameterSource parameters = null;
        try {
            parameters = new MapSqlParameterSource()
                    .addValue("name", product.getName())
                    .addValue("price", product.getPrice())
                    .addValue("quantity", product.getQuantityOnStock())
                    .addValue("rating", product.getRating());

            if (product.getImgBytes() != null) {
                parameters.addValue("imgbytes", new SerialBlob(product.getImgBytes()));
            } else {
                parameters.addValue("imgbytes", null);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        jdbcTemplate.update(INSERT_PRODUCT, parameters);

    }

    public Product getProductById(int id) {
        String getProd = GET_PRODUCT_BY_ID + id;
        List<Product> result = jdbcTemplate.query(getProd, (rs, rowNum) -> new Product(rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getInt("rating"),
                rs.getInt("quantity"),
                rs.getBytes("imgBytes")));
        return result.get(0);

    }

    public void deleteProduct(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID, parameters);
    }

    public List<Product> getProducts() {

        return jdbcTemplate.query(FETCH_ALL_PRODUCTS, (rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getInt("rating"),
                rs.getInt("quantity"),
                rs.getBytes("imgBytes")
        ));
    }

    public void update(int id, Product product) {
        String updateQuery = "update product set name = :name, price = :price, quantity = :quantity, rating = :rating, imgBytes = :imgBytes where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("quantity", product.getQuantityOnStock())
                .addValue("rating", product.getRating())
                .addValue("imgBytes", product.getImgBytes());

        jdbcTemplate.update(updateQuery, parameters);
    }
}
