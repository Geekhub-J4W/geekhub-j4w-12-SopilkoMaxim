package edu.geekhub.user;


import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public boolean validateName(User user, Logger logger) {

        if (user.getFullName().isBlank()) {
            logger.warning("User with id " + user.getId() + " can not create, because Name is empty");
            return false;
        }

        for (char c : user.getFullName().toCharArray())
            if (Character.isDigit(c)) {
                logger.warning("user with id " + user.getId() + " can not create, because Name contain digit");
                return false;
            }


        return true;

    }

    public final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateEmail(User user) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail());
        return matcher.matches();
    }

    public boolean validateIdExist(int id, UserRepository repository, Logger logger){
        for (User user : repository.getUsers()) {
            if(user.getId()==id)
                return true;
        }
        logger.warning("user with id "+ id + " doesn't exist");
        return false;

    }
}
