package edu.geekhub.product.image;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ImageRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_IMAGE = """
            INSERT INTO image (name,originalfilename,size,contenttype,bytes) VALUES (:name,:originalfilename, :size, :contenttype, :bytes)
            """;

    public static final String GET_IMAGE_BY_ID = """
            SELECT * FROM image WHERE id = 
            """;
    public static final String DELETE_BY_ID = """
            DELETE FROM image WHERE id = :id
            """;
    public static final String GET_IMAGE_BY_NAME = """
            SELECT * FROM image WHERE originalfilename = 
            """;

    public void addImage(Image image) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", image.getName())
                .addValue("originalfilename", image.getOriginalFileName())
                .addValue("size",image.getSize())
                .addValue("contenttype",image.getContentType())
                .addValue("bytes",image.getBytes());
        jdbcTemplate.update(INSERT_IMAGE, parameters);
    }

    public Image getImageById(int id) {
        String getImage = GET_IMAGE_BY_ID + id;
        List<Image> result = jdbcTemplate.query(getImage, (rs, rowNum) -> new Image(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("originalfilename"),
                rs.getInt("size"),
                rs.getString("contenttype"),
                rs.getBytes("bytes")));
        return result.get(0);
    }

    public Image getImageByOrigName(String name) {
        String getImage = GET_IMAGE_BY_NAME + name;
        List<Image> result = jdbcTemplate.query(getImage, (rs, rowNum) -> new Image(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("originalfilename"),
                rs.getInt("size"),
                rs.getString("contenttype"),
                rs.getBytes("bytes")));
        return result.get(0);
    }

    public void deleteImage(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID, parameters);
    }


}