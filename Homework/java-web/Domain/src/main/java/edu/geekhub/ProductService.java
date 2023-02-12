package edu.geekhub;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ProductService {

    private static final Logger logger = Logger.getLogger(Product.class.getName());
    private final ProductRepository repository = new ProductRepository();

    public void addProduct(Product product) {
        if (ProductValidator.validateName(product, logger) && ProductValidator.validatePrice(product, logger)) {
            logger.info("Product " + product.getName() + " with Id " + product.getId() + " with price "
                    + product.getPrice() + " was added to Database");
            repository.addProduct(product);
        }
    }

    public void deleteProduct(int id) {
        if(ProductValidator.validateIdExist(id,repository,logger)) {
            Product product = repository.getProductById(id);
            logger.info("Product " + product.getName() + " with Id " + product.getId() + " with price "
                    + product.getPrice() + " was deleted from Database");
            repository.deleteProduct(id);
        }
    }

    public Product getProductById(int id)
    {
        if(ProductValidator.validateIdExist(id,repository,logger))
            return repository.getProductById(id);
        else {
            System.out.println("Wrong Id try again");
            Scanner scan =new Scanner(System.in);
            int newId=scan.nextInt();
            return this.getProductById(newId);
        }
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }

    public void showSortedListByPrice()
    {
        var sortedList = repository.getProducts();
        Collections.sort(sortedList,Product.COMPARE_BY_PRICE);
        System.out.println(sortedList);
    }

    public void showSortedListByName()
    {
        var sortedList = repository.getProducts();
        Collections.sort(sortedList,Product.COMPARE_BY_NAME);
        System.out.println(sortedList);
    }
}
