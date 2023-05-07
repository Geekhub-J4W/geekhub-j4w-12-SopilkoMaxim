package edu.geekhub.Service;

import edu.geekhub.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import edu.geekhub.Entity.User;


import java.util.Optional;
import java.util.logging.Logger;

@Component
public class UserService {
    private static final Logger logger = Logger.getLogger(edu.geekhub.Entity.User.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CryptoCoinService cryptoCoinService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (UserValidator.validateName(user, logger) && UserValidator.validateAge(user, logger)&&UserValidator.validateEmail(user,logger)) {
            logger.info("User with id " + user.getId() +" with name: " + user.getName() + " was add to database");
            userRepository.addUser(user);
        }
    }

   public void updateUser(User user){
        logger.info("User with id "+ user.getId() + " was updated");
        userRepository.updateUser(user.getId(),user);
        //userRepository.updateUserWallet(user.getWallet().getId(),user.getWallet());
   }

    public Optional<User> getUserByEmail(String email) {

        return Optional.ofNullable(userRepository.getUserByEmail(email));
    }
}
