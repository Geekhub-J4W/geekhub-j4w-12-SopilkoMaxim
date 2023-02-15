package edu.geekhub.homework;

import edu.geekhub.exceptions.ConnectionInterruptedException;
import edu.geekhub.models.User;
import edu.geekhub.storage.Repository;
import edu.geekhub.storage.MemoryStorage;

// Don't move this class
public class UserService {

    private final Repository repository;
    private final UserValidator userValidator;

    public UserService() {
        this.repository = new MemoryStorage();
        this.userValidator = new UserValidator();
    }

    public UserService(Repository memoryStorage) {
        this.repository = memoryStorage;
        this.userValidator=new UserValidator();
    }


    public boolean add(User user){
        try {
            userValidator.validate(user);
            repository.tryToAdd(user);
            return true;
        } catch (ConnectionInterruptedException e){
            System.out.println(e.getMessage());
            return false;
        }

    }
}
