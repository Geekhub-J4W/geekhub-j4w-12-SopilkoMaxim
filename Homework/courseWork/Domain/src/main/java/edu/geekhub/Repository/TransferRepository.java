package edu.geekhub.Repository;

import edu.geekhub.Entity.Transfer;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransferRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TransferRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (coinName, amount, date, price, user_id, operation) " +
                "VALUES (:coinName, :amount, :date, :price, :userId, :operation)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("coinName", transfer.getCoinName())
                .addValue("amount",transfer.getAmount())
                .addValue("date", transfer.getDate())
                .addValue("price", transfer.getPrice())
                .addValue("userId", transfer.getUserId())
                .addValue("operation",transfer.getOperation());
        jdbcTemplate.update(sql, parameters);
    }

    public List<Transfer> getTransfersByUserId(int userId) {
        String query = "SELECT id, coinName, amount, date, price, operation FROM transfer WHERE user_id = :userId";
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("userId", userId);
        List<Transfer> result = jdbcTemplate.query(query, parameters, (rs, rowNum) -> new Transfer(
                rs.getLong("id"),
                rs.getString("coinName"),
                rs.getFloat("amount"),
                rs.getTimestamp("date").toLocalDateTime(),
                rs.getFloat("price"),
                rs.getString("operation")
        ));
        return result;
    }
    public List<Transfer> getTransfersByUserIdAndCoinName(int userId, String coinName) {
        String query = "SELECT id, coinName, amount, date, price, operation FROM transfer WHERE user_id = :userId AND coinName = :coinName";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("coinName", coinName);
        List<Transfer> result = jdbcTemplate.query(query, parameters, (rs, rowNum) -> new Transfer(
                rs.getLong("id"),
                rs.getString("coinName"),
                rs.getFloat("amount"),
                rs.getTimestamp("date").toLocalDateTime(),
                rs.getFloat("price"),
                rs.getString("operation")
        ));
        return result;
    }

}
