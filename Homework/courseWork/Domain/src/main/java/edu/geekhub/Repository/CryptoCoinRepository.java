package edu.geekhub.Repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CryptoCoinRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT_COIN = """
            INSERT INTO coin (date, Lido_Staked_Ether, Bitcoin,Ethereum,BNB,XRP,Polygon,Tether,USD_Coin,Cardano,Dogecoin) 
            VALUES (:date, :Lido_Staked_Ether, :Bitcoin,:Ethereum,:BNB,:XRP,:Polygon,:Tether,:USD_Coin,:Cardano,:Dogecoin)
            """;

    public CryptoCoinRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void addCoin(HashMap<String, Float> values, LocalDateTime date) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("date", date)
                .addValue("Lido_Staked_Ether", values.get("Lido Staked Ether"))
                .addValue("Bitcoin",values.get("Bitcoin"))
                .addValue("Ethereum",values.get("Ethereum"))
                .addValue("BNB",values.get("BNB"))
                .addValue("XRP",values.get("XRP"))
                .addValue("Polygon",values.get("Polygon"))
                .addValue("Tether",values.get("Tether"))
                .addValue("USD_Coin",values.get("USD Coin"))
                .addValue("Cardano",values.get("Cardano"))
                .addValue("Dogecoin",values.get("Dogecoin"));
        jdbcTemplate.update(INSERT_COIN, parameters);
    }

    public Map<Date, Float> listOfBtc() {
        String sql = "SELECT date, Bitcoin FROM coin";
        Map<Date, Float> result = jdbcTemplate.query(sql, rs -> {
            Map<Date, Float> map = new HashMap<>();
            while (rs.next()) {
                Date date = rs.getTimestamp("date");
                Float btcValue = rs.getFloat("Bitcoin");
                map.put(date, btcValue);
            }
            return map;
        });
        return result;
    }

    public Map<Date, Float> getByName(String name) {
        String sql = "SELECT date, "+name+" FROM coin ORDER BY date";
        Map<Date, Float> result = jdbcTemplate.query(sql, rs -> {
            Map<Date, Float> map = new HashMap<>();
            while (rs.next()) {
                Date date = rs.getTimestamp("date");
                Float coin = rs.getFloat(name);
                map.put(date, coin);
            }
            return map;
        });
        return result.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, HashMap::new));
    }


//    public float getPriceByName(String coinName){
//
//        String sql = "SELECT " + coinName + " FROM coin WHERE date = (SELECT MAX(date) FROM coin)";
//
//        return jdbcTemplate.queryForObject(sql, Float.class);
//    }

}
