package edu.geekhub.Service;

import edu.geekhub.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import edu.geekhub.Entity.User;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class UserService {
    private static final Logger logger = Logger.getLogger(edu.geekhub.Entity.User.class.getName());
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public CryptoCoinService cryptoCoinService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        logger.info("User with id " + user.getId() + " with name: " + user.getName() + " was add to database");
        userRepository.addUser(user);

    }

    public void updateUser(User user) {
        logger.info("User with id " + user.getId() + " was updated");
        userRepository.updateUser(user.getId(), user);
    }

    public Optional<User> getUserByEmail(String email) {

        return Optional.ofNullable(userRepository.getUserByEmail(email));
    }

    public float getUserTotalBalance(User user) {
        return user.getWallet().getBitcoin() * cryptoCoinService.getLastPriceByName("bitcoin") +
                user.getWallet().getLse() * cryptoCoinService.getLastPriceByName("Lido_Staked_Ether") +
                user.getWallet().getBnb() * cryptoCoinService.getLastPriceByName("bnb") +
                user.getWallet().getEthereum() * cryptoCoinService.getLastPriceByName("ethereum") +
                user.getWallet().getXrp() * cryptoCoinService.getLastPriceByName("xrp") +
                user.getWallet().getSolana() * cryptoCoinService.getLastPriceByName("solana") +
                user.getWallet().getUsd() * cryptoCoinService.getLastPriceByName("usd_coin") +
                user.getWallet().getCardano() * cryptoCoinService.getLastPriceByName("cardano") +
                user.getWallet().getDogecoin() * cryptoCoinService.getLastPriceByName("dogecoin") +
                user.getWallet().getTether() * cryptoCoinService.getLastPriceByName("tether") +
                user.getBalance();
    }

    public void updateRating(User user) {
        user.setRating(getUserTotalBalance(user) - 1_000_000);
        userRepository.updateUser(user.getId(), user);
    }

    public Map<String, Float> getSortedUserRatings() {

        return userRepository.getSortedUserRatings();
    }

    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }
}
