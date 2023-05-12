package edu.geekhub.Repository;

import edu.geekhub.Entity.User;
import edu.geekhub.Entity.Wallet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;


import java.sql.ResultSet;
import java.util.*;

@Component
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    private static final String INSERT_USER = """
            INSERT INTO userdb (name,age,email,password,balance,rating,role,status,id_wallet) 
            VALUES (:name,:age,:email,:password,:balance,:rating,:role,:status,:id_wallet)
            """;
    private static final String INSERT_WALLET = """
            INSERT INTO wallet (Lido_Staked_Ether, Bitcoin, Ethereum, BNB, XRP, Solana, Tether, USD_Coin, Cardano, Dogecoin)
            VALUES (:Lido_Staked_Ether, :Bitcoin, :Ethereum, :BNB, :XRP, :Solana, :Tether, :USD_Coin, :Cardano, :Dogecoin)
            RETURNING id
            """;

    public static final String FETCH_ALL_USERS = """
            SELECT u.*, w.* FROM userdb u
            LEFT JOIN wallet w ON u.id_wallet = w.id
            """;

    public static final String GET_USER_BY_ID = """
            SELECT u.id, u.name, u.age, u.email, u.password, u.balance, u.rating, u.role, u.status,
                   w.Lido_Staked_Ether, w.Bitcoin, w.Ethereum, w.BNB, w.XRP, w.Solana, w.Tether, w.USD_Coin, w.Cardano, w.Dogecoin
            FROM userdb u
            JOIN wallet w ON u.id_wallet = w.id
            WHERE u.id = :id
            """;
    public static final String GET_USER_BY_EMAIL = """
            SELECT u.id, u.name, u.age, u.email, u.password, u.balance, u.rating, u.role, u.status,w.id AS wallet_id,
                   w.Lido_Staked_Ether, w.Bitcoin, w.Ethereum, w.BNB, w.XRP, w.Solana, w.Tether, w.USD_Coin, w.Cardano, w.Dogecoin
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

        GeneratedKeyHolder userKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_USER, userParams, userKeyHolder);

        List<Map<String, Object>> userKeys = userKeyHolder.getKeyList();
        int userId = (int) userKeys.get(0).get("id");

        MapSqlParameterSource walletParams = new MapSqlParameterSource()
                .addValue("Lido_Staked_Ether", 0)
                .addValue("Bitcoin", 0)
                .addValue("Ethereum", 0)
                .addValue("BNB", 0)
                .addValue("XRP", 0)
                .addValue("Solana", 0)
                .addValue("Tether", 0)
                .addValue("USD_Coin", 0)
                .addValue("Cardano", 0)
                .addValue("Dogecoin", 0);

        GeneratedKeyHolder walletKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_WALLET, walletParams, walletKeyHolder);

        List<Map<String, Object>> walletKeys = walletKeyHolder.getKeyList();
        int walletId = (int) walletKeys.get(0).get("id");

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
                        rs.getFloat("BNB"), rs.getFloat("XRP"), rs.getFloat("Solana"), rs.getFloat("Tether"),
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
                new Wallet(rs.getInt("wallet_id"),rs.getFloat("Lido_Staked_Ether"), rs.getFloat("Bitcoin"), rs.getFloat("Ethereum"),
                        rs.getFloat("BNB"), rs.getFloat("XRP"), rs.getFloat("Solana"), rs.getFloat("Tether"),
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
                    rs.getFloat("Solana"),
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
        String updateQuery = "update userdb set name = :name, age = :age,email = :email, password = :password, balance = :balance, rating = :rating, role = :role, status = :status where id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", user.getName())
                .addValue("age", user.getAge())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("balance", user.getBalance())
                .addValue("rating", user.getRating())
                .addValue("role", user.getRole().toString())
                .addValue("status", user.getStatus().toString());

        jdbcTemplate.update(updateQuery, parameters);


        String updateQueryWallet = "UPDATE wallet SET Lido_Staked_Ether = :lidoStakedEther, Bitcoin = :bitcoin, Ethereum = :ethereum, BNB = :bnb, XRP = :xrp, solana = :solana, Tether = :tether, USD_Coin = :usdCoin, Cardano = :cardano, Dogecoin = :dogecoin WHERE id = :id";
        Wallet wallet = user.getWallet();
        MapSqlParameterSource parametersWallet = new MapSqlParameterSource()
                .addValue("id", wallet.getId())
                .addValue("lidoStakedEther", wallet.getLse())
                .addValue("bitcoin", wallet.getBitcoin())
                .addValue("ethereum", wallet.getEthereum())
                .addValue("bnb", wallet.getBnb())
                .addValue("xrp", wallet.getXrp())
                .addValue("solana", wallet.getSolana())
                .addValue("tether", wallet.getTether())
                .addValue("usdCoin", wallet.getUsd())
                .addValue("cardano", wallet.getCardano())
                .addValue("dogecoin", wallet.getDogecoin());

        jdbcTemplate.update(updateQueryWallet, parametersWallet);
    }

    public void updateUserWallet(int walletId, Wallet wallet) {
        String updateQuery = "UPDATE wallet SET Lido_Staked_Ether = :lidoStakedEther, Bitcoin = :bitcoin, Ethereum = :ethereum, BNB = :bnb, XRP = :xrp, solana = :solana, Tether = :tether, USD_Coin = :usdCoin, Cardano = :cardano, Dogecoin = :dogecoin WHERE id = :id";

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", walletId)
                .addValue("lidoStakedEther", wallet.getLse())
                .addValue("bitcoin", wallet.getBitcoin())
                .addValue("ethereum", wallet.getEthereum())
                .addValue("bnb", wallet.getBnb())
                .addValue("xrp", wallet.getXrp())
                .addValue("solana", wallet.getSolana())
                .addValue("tether", wallet.getTether())
                .addValue("usdCoin", wallet.getUsd())
                .addValue("cardano", wallet.getCardano())
                .addValue("dogecoin", wallet.getDogecoin());

        jdbcTemplate.update(updateQuery, parameters);
    }

    public Map<String, Float> getSortedUserRatings() {
        String sql = "SELECT name, rating FROM userdb ORDER BY rating DESC";
        Map<String, Float> userRatings = new LinkedHashMap<>();
        jdbcTemplate.query(sql, (ResultSet rs) -> {
            String name = rs.getString("name");
            Float rating = rs.getFloat("rating");
            userRatings.put(name, rating);
        });
        return userRatings;
    }

}
