package edu.geekhub.Repository;

import edu.geekhub.Entity.User;
import edu.geekhub.Entity.Wallet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_USER = """
        INSERT INTO userdb (name,age,email,password,balance,rating,role,status,id_wallet) 
        VALUES (:name,:age,:email,:password,:balance,:rating,:role,:status,:id_wallet)
        """;
    private static final String INSERT_WALLET = """
        INSERT INTO wallet (Lido_Staked_Ether, Bitcoin, Ethereum, BNB, XRP, Polygon, Tether, USD_Coin, Cardano, Dogecoin)
        VALUES (:Lido_Staked_Ether, :Bitcoin, :Ethereum, :BNB, :XRP, :Polygon, :Tether, :USD_Coin, :Cardano, :Dogecoin)
        RETURNING id
        """;

    public static final String FETCH_ALL_USERS = """
            SELECT u.*, w.* FROM userdb u
            LEFT JOIN wallet w ON u.id_wallet = w.id
            """;

    public static final String GET_USER_BY_ID = """
            SELECT u.id, u.name, u.age, u.email, u.password, u.balance, u.rating, u.role, u.status,
                   w.Lido_Staked_Ether, w.Bitcoin, w.Ethereum, w.BNB, w.XRP, w.Polygon, w.Tether, w.USD_Coin, w.Cardano, w.Dogecoin
            FROM userdb u
            JOIN wallet w ON u.id_wallet = w.id
            WHERE u.id = :id
            """;
    public static final String GET_USER_BY_EMAIL = """
            SELECT u.id, u.name, u.age, u.email, u.password, u.balance, u.rating, u.role, u.status,
                   w.Lido_Staked_Ether, w.Bitcoin, w.Ethereum, w.BNB, w.XRP, w.Polygon, w.Tether, w.USD_Coin, w.Cardano, w.Dogecoin
            FROM userdb u
            JOIN wallet w ON u.id_wallet = w.id
            WHERE u.email = :email
            """;
    public static final String DELETE_BY_ID = """
        DELETE FROM userdb WHERE id = :id;
        DELETE FROM walletdb WHERE user_id = :id;
        """;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addUser(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource userParams = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("balance", user.getBalance())
                .addValue("rating", user.getBalance())
                .addValue("role", user.getRole().toString())
                .addValue("status", user.getStatus().toString())
                .addValue("id_wallet", null);

        jdbcTemplate.update(INSERT_USER, userParams, keyHolder);

        long userId = keyHolder.getKey().longValue();

        MapSqlParameterSource walletParams = new MapSqlParameterSource()
                .addValue("Lido_Staked_Ether", 0)
                .addValue("Bitcoin", 0)
                .addValue("Ethereum", 0)
                .addValue("BNB", 0)
                .addValue("XRP", 0)
                .addValue("Polygon", 0)
                .addValue("Tether", 0)
                .addValue("USD_Coin", 0)
                .addValue("Cardano", 0)
                .addValue("Dogecoin", 0);

        GeneratedKeyHolder walletKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_WALLET, walletParams, walletKeyHolder);

        long walletId = walletKeyHolder.getKey().longValue();

        MapSqlParameterSource updateParams = new MapSqlParameterSource()
                .addValue("id_wallet", walletId)
                .addValue("id", userId);
        user.getWallet().setId((int) walletId);
        jdbcTemplate.update("UPDATE userdb SET id_wallet = :id_wallet WHERE id = :id", updateParams);
    }

    public User getUserById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
        List<User> result = jdbcTemplate.query(GET_USER_BY_ID, parameters, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getFloat("balance"),
                rs.getFloat("rating"),
                rs.getString("role"),
                rs.getString("status"),
                new Wallet(rs.getFloat("Lido_Staked_Ether"), rs.getFloat("Bitcoin"), rs.getFloat("Ethereum"),
                        rs.getFloat("BNB"), rs.getFloat("XRP"), rs.getFloat("Polygon"), rs.getFloat("Tether"),
                        rs.getFloat("USD_Coin"), rs.getFloat("Cardano"), rs.getFloat("Dogecoin"))
        ));
        return result.isEmpty() ? null : result.get(0);
    }

    public User getUserByEmail(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
        List<User> result = jdbcTemplate.query(GET_USER_BY_EMAIL, parameters, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getFloat("balance"),
                rs.getFloat("rating"),
                rs.getString("role"),
                rs.getString("status"),
                new Wallet(rs.getFloat("Lido_Staked_Ether"), rs.getFloat("Bitcoin"), rs.getFloat("Ethereum"),
                        rs.getFloat("BNB"), rs.getFloat("XRP"), rs.getFloat("Polygon"), rs.getFloat("Tether"),
                        rs.getFloat("USD_Coin"), rs.getFloat("Cardano"), rs.getFloat("Dogecoin"))
        ));
        return result.isEmpty() ? null : result.get(0);
    }

    public void deleteUser(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update(DELETE_BY_ID, parameters);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Map<Integer, User> userMap = new HashMap<>();

        jdbcTemplate.query(FETCH_ALL_USERS, rs -> {
            int userId = rs.getInt("id");
            if (!userMap.containsKey(userId)) {
                User user = new User(
                        userId,
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getFloat("balance"),
                        rs.getFloat("rating"),
                        rs.getString("role"),
                        rs.getString("status"),
                        null
                );
                userMap.put(userId, user);
                users.add(user);
            }

            Wallet wallet = new Wallet(
                    rs.getFloat("Lido_Staked_Ether"),
                    rs.getFloat("Bitcoin"),
                    rs.getFloat("Ethereum"),
                    rs.getFloat("BNB"),
                    rs.getFloat("XRP"),
                    rs.getFloat("Polygon"),
                    rs.getFloat("Tether"),
                    rs.getFloat("USD_Coin"),
                    rs.getFloat("Cardano"),
                    rs.getFloat("Dogecoin")
            );
            userMap.get(userId).setWallet(wallet);
        });

        return users;
    }

    public void updateUser(int id, User user) {
        String updateQuery="update userdb set name = :name, age = :age,email = :email, password = :password, balance = :balance, rating = :rating, role = :role, status = :status where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("name",user.getName())
                .addValue("age",user.getAge())
                .addValue("email",user.getEmail())
                .addValue("password",user.getPassword())
                .addValue("balance",user.getBalance())
                .addValue("rating",user.getRating())
                .addValue("role",user.getRole().toString())
                .addValue("status",user.getStatus().toString());

        jdbcTemplate.update(updateQuery,parameters);
    }
    public void updateUserWallet(int walletId, Wallet wallet) {
        String updateQuery = "UPDATE wallet SET Lido_Staked_Ether = :lidoStakedEther, Bitcoin = :bitcoin, Ethereum = :ethereum, BNB = :bnb, XRP = :xrp, Polygon = :Polygon, Tether = :tether, USD_Coin = :usdCoin, Cardano = :cardano, Dogecoin = :dogecoin WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", walletId)
                .addValue("lidoStakedEther", wallet.getLse())
                .addValue("bitcoin", wallet.getBitcoin())
                .addValue("ethereum", wallet.getEthereum())
                .addValue("bnb", wallet.getBnb())
                .addValue("xrp", wallet.getXrp())
                .addValue("Polygon", wallet.getPolygon())
                .addValue("tether", wallet.getTether())
                .addValue("usdCoin", wallet.getUsd())
                .addValue("cardano", wallet.getCardano())
                .addValue("dogecoin", wallet.getDogecoin());

        jdbcTemplate.update(updateQuery, parameters);
    }


}
