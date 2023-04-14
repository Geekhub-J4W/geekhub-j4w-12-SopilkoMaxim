package edu.geekhub.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class UserService {
    private static final Logger logger = Logger.getLogger(User.class.getName());

    private final UserRepository repository;
    private Scanner scan = new Scanner(System.in);

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public void addUser(User user) {
        if (UserValidator.validateName(user, logger) && UserValidator.validateEmail(user)) {
            logger.info("User " + user.getFullName() + " with Id " + user.getId() +
                    "was added to Database");
            repository.addUser(user);
        }
    }

    public void deleteUser(int id) {
        if (UserValidator.validateIdExist(id, repository, logger)) {
            User user = repository.getUserById(id);
            logger.info("User " + user.getFullName() + " with Id " + user.getId() +
                    " was deleted from Database");
            repository.deleteUser(id);
        }
    }

    public User getUserById(int id) {
        if (UserValidator.validateIdExist(id, repository, logger))
            return repository.getUserById(id);
        else {
            System.out.println("Wrong Id try again");
            int newId = scan.nextInt();
            return this.getUserById(newId);
        }
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
}
