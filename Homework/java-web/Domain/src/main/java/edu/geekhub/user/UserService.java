package edu.geekhub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = Logger.getLogger(User.class.getName());

    private UserValidator validator;

    private final UserRepository repository;


    @Autowired
    public UserService(UserRepository repository) {
        this.validator= new UserValidator();
        this.repository = repository;
    }



    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (validator.validateName(user, logger) && validator.validateEmail(user)) {
            logger.info("User " + user.getFullName() + " with Id " + user.getId() +
                    "was added to Database");
            repository.addUser(user);
        }
    }

    public void deleteUser(int id) {
        if (validator.validateIdExist(id, repository, logger)) {
            User user = repository.getUserById(id);
            logger.info("User " + user.getFullName() + " with Id " + user.getId() +
                    " was deleted from Database");
            repository.deleteUser(id);
        }
    }

    public User getUserById(int id) {
        if (validator.validateIdExist(id, repository, logger))
            return repository.getUserById(id);
        else
            return null;
    }

    public Optional<User> getUserByEmail(String email) {
        return Optional.of(repository.getUserByEmail(email));
    }

    public List<User> getUsers() {
        return repository.getUsers();
    }

    public void update(int id, User user) {
        repository.update(id, user);
    }

    public void setValidator(UserValidator validator) {
        this.validator=validator;
    }
}
