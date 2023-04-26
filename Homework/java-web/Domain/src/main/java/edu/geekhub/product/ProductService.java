package edu.geekhub.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Component
public class ProductService {

    private static final Logger logger = Logger.getLogger(Product.class.getName());

    private final ProductRepository repository;

    private ProductValidator validator;

    public ProductService(ProductRepository productRepository) {

        this.repository = productRepository;
        this.validator = new ProductValidator();
    }

    public void setValidator(ProductValidator validator) {
        this.validator = validator;
    }

    public Product addImage(Product product, MultipartFile file) {

        if (file.getSize() != 0) {
            product.setImgBytes(file);
        }
        return product;
    }

    public void addProduct(Product product) {
        if (validator.validateName(product, logger) && validator.validatePrice(product, logger)) {
            logger.info("Product " + product.getName() + " with Id " + product.getId() + " with price "
                    + product.getPrice() + " was added to Database");
            repository.addProduct(product);
        }
    }



    public void deleteProduct(int id) {
        if (validator.validateIdExist(id, repository, logger)) {
            Product product = repository.getProductById(id);
            logger.info("Product " + product.getName() + " with Id " + product.getId() + " with price "
                    + product.getPrice() + " was deleted from Database");
            repository.deleteProduct(id);
        }
    }

    public Product getProductById(int id) {
        if (validator.validateIdExist(id, repository, logger))
            return repository.getProductById(id);
        else {
            System.out.println("Wrong Id try again");
            Scanner scan = new Scanner(System.in);
            int newId = scan.nextInt();
            return this.getProductById(newId);
        }
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }

    public List<Product> sortedListByPrice() {
        var sortedList = repository.getProducts();
        Collections.sort(sortedList, Product.COMPARE_BY_PRICE);
        return sortedList;
    }

    public List<Product> sortedListByName() {
        var sortedList = repository.getProducts();
        Collections.sort(sortedList, Product.COMPARE_BY_NAME);
        return sortedList;
    }

    public void update(int id, Product product) {
        repository.update(id, product);
    }
}
