package edu.geekhub.homework;

import edu.geekhub.exceptions.ConnectionInterruptedException;
import edu.geekhub.models.User;
import edu.geekhub.storage.MemoryStorage;
import edu.geekhub.storage.Repository;

public class UserValidator {


    private final Repository userRepository;

    User[] users;

    public UserValidator() {
        userRepository = new MemoryStorage();
        try {
            users = userRepository.tryToGetAll();
        } catch (ConnectionInterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void validate(User user){
        validateUserId(user);
        validateUserEmail(user);
        validateUserName(user);
        validateUserFullName(user);
        validateUserAge(user);
        validateUserNotes(user);
        validateUserAmountOfFollowers(user);

    }


    private void validateUserId(User user) {
        if(user.getId().toString().isBlank())
            throw new IllegalArgumentException("User id is blank");

        if(userIdExist(user))
            throw new IllegalArgumentException("User with this Id already exist");

    }
    private boolean userIdExist(User user){
        for (User user1 : users) {
            if (user1.getId().equals(user.getId()))
                return true;
        }
        return false;
    }

    private void validateUserEmail(User user) {
        if(user.getEmail().isBlank())
            throw new IllegalArgumentException("User email is blank");

        if(user.getEmail().contains("\\/")||user.getEmail().contains("(")||user.getEmail().contains(")")||
                user.getEmail().contains("{")||user.getEmail().contains("}"))
            throw new IllegalArgumentException("User name contains some of forbidden symbols (){}\\/");

        if(userEmailExist(user))
            throw new IllegalArgumentException("User with this email already exist");

        if(!user.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@\" \n" +
                "        + \"[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$"))
            throw new IllegalArgumentException("User email is not valid");

    }

    private boolean userEmailExist(User user) {
        for (User user1 : users) {
            if (user1.getEmail().equals(user.getEmail()))
                return true;
        }
        return false;
    }

    private void validateUserName(User user) {
        if(user.getUserName().isBlank())
            throw new IllegalArgumentException("User name is blank");

        if(user.getUserName().contains("\\/")||user.getUserName().contains("(")||user.getUserName().contains(")")||
                user.getUserName().contains("{")||user.getUserName().contains("}"))
            throw new IllegalArgumentException("User name contains some of forbidden symbols (){}\\/");

        if(!user.getUserName().equals(user.getUserName().toLowerCase()))
            throw new IllegalArgumentException("User name has upper case, should be in lower case");

        if(userNameExist(user))
            throw new IllegalArgumentException("User name already exist");

    }

    private boolean userNameExist(User user) {
        for (User user1 : users) {
            if (user1.getUserName().equals(user.getUserName()))
                return true;
        }
        return false;
    }

    private void validateUserFullName(User user) {
        if (user.getFullName().isBlank())
            throw new IllegalArgumentException("User full name is blank");

        if(user.getFullName().contains("\\d"))
            throw new IllegalArgumentException("User full name contains digits");

        if(!user.getFullName().matches("\\w+\\s\\w+$"))
            throw new IllegalArgumentException("User full name should have two words");

        if(user.getFullName().matches("[a-z]+\\s\\b([A=Z][a=z]*)\\b"));
            throw new IllegalArgumentException("User full name should be written in camel case");
    }

    private void validateUserAge(User user) {
        if (user.getAge().toString().isBlank())
            throw new IllegalArgumentException("User age is blank");

        if(user.getAge() <= 18 || user.getAge() >= 100)
            throw new IllegalArgumentException("User age must be over 18 and less then 100");
    }

    private void validateUserNotes(User user) {
        if(user.getNotes().isBlank())
            throw new IllegalArgumentException("User notes is blank");

        if(user.getNotes().length()>255)
            throw new IllegalArgumentException("User notes should be less than 255 symbols");
    }

    private void validateUserAmountOfFollowers(User user) {
        if (user.getAmountOfFollowers().toString().isBlank())
            throw new IllegalArgumentException("User amount of followers is blank");

        if(user.getAmountOfFollowers()<0)
            throw new IllegalArgumentException("User followers less than zero");
    }
}
