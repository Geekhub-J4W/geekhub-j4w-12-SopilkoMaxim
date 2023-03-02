package edu.geekhub.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
@Component
public class ProductService {

    private static final Logger logger = Logger.getLogger(Product.class.getName());
    //@Autowired
    private final ProductRepository repository;

    /*@PostConstruct
    public void initBD(){
        repository.addProduct(new Product("First",50));
        repository.addProduct(new Product("Second",40));
        repository.addProduct(new Product("Third",30));
        repository.addProduct(new Product("Fourth",60));
        repository.addProduct(new Product("Fifth",70));
    }*/
    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

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

    public List<Product> sortedListByPrice()
    {
        var sortedList = repository.getProducts();
        Collections.sort(sortedList, Product.COMPARE_BY_PRICE);
        return sortedList;
    }

    public List<Product> sortedListByName()
    {
        var sortedList = repository.getProducts();
        Collections.sort(sortedList, Product.COMPARE_BY_NAME);
        return sortedList;
    }
}
