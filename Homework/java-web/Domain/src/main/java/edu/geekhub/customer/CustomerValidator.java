package edu.geekhub.customer;

import edu.geekhub.product.Product;
import edu.geekhub.product.ProductRepository;

import java.util.logging.Logger;

public class CustomerValidator {
    public static boolean validateName(Customer customer, Logger logger) {

        if (customer.getName().isBlank()) {
            logger.warning("Product with id " + customer.getId() + " can not create, because Name is empty");
            return false;
        }

        for (char c : customer.getName().toCharArray())
            if (Character.isDigit(c)) {
                logger.warning("Product with id " + customer.getId() + " can not create, because Name contain digit");
                return false;
            }


        return true;

    }

    public static boolean validateAge(Customer customer, Logger logger) {
        if(customer.getAge() < 0)
        {
            logger.warning("Customer with id " + customer.getId() + " can not create, because age less than 0");
            return false;
        }
        if(customer.getAge() > 100)
        {
            logger.warning("Customer with id " + customer.getId() + " can not create, because age more than 100");
            return false;
        }
        return true;

    }

    public static boolean validateIdExist(int id, CustomerRepository customerRepository, Logger logger){
        for (Customer customer : customerRepository.getCustomers()) {
            if(customer.getId()==id)
                return true;
        }
        logger.warning("Customer with id "+ id + " doesn't exist");
        return false;

    }
}
