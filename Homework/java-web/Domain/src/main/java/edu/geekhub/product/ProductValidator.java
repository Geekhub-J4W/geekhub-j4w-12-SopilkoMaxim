package edu.geekhub.product;

import java.util.logging.Logger;

public class ProductValidator {


    public static boolean validateName(Product product, Logger logger) {

        if (product.getName().isBlank()) {
            logger.warning("Product with id " + product.getId() + " can not create, because Name is empty");
            return false;
        }

        for (char c : product.getName().toCharArray())
            if (Character.isDigit(c)) {
                logger.warning("Product with id " + product.getId() + " can not create, because Name contain digit");
                return false;
            }

        return true;

    }

    public static boolean validatePrice(Product product, Logger logger) {
        if(product.getPrice() < 0)
        {
            logger.warning("Product with id " + product.getId() + " can not create, because price less than 0");
            return false;
        }
        return true;

    }

    public static boolean validateIdExist(int id, ProductRepository productRepository, Logger logger){
        for (Product product : productRepository.getProducts()) {
            if(product.getId()==id)
                return true;
        }
        logger.warning("Product with id "+ id + " doesn't exist");
        return false;

    }
}
