package edu.geekhub.Service;

import edu.geekhub.Entity.User;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateName(User user, Logger logger) {
        if (user.getName().isBlank()) {
            logger.warning("User with id " + user.getId() + " can not create, because Name is empty");
            return false;
        }

        for (char c : user.getName().toCharArray())
            if (Character.isDigit(c)) {
                logger.warning("User with id " + user.getId() + " can not create, because Name contain digit");
                return false;
            }
        String expression = "^[a-zA-Z\\s]+";
        if(!user.getName().matches(expression)) {
            logger.warning("User with id " + user.getId() + " can not create, because Name is wrong");
            return false;
        }
        return true;
    }

    public static boolean validateAge(User user, Logger logger) {
        if(user.getAge() < 18)
        {
            logger.warning("User with id " + user.getId() + " can not create, because age less than 18");
            return false;
        }
        if(user.getAge() > 100)
        {
            logger.warning("User with id " + user.getId() + " can not create, because age more than 100");
            return false;
        }
        return true;
    }

    public static boolean validateEmail(User user, Logger logger) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail());
        return matcher.matches();
    }
}
