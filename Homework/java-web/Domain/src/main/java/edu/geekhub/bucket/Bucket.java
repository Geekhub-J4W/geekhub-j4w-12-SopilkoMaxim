package edu.geekhub.bucket;

import edu.geekhub.product.Product;
import edu.geekhub.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Scope("prototype")
public class Bucket {
    private User customer;
    private List<Product> products = new ArrayList<>();

    public void setCustomer(User customer)
    {
        this.customer = customer;
    }

    public void addProduct(Product product)
    {
        products.add(product);
    }

    public void deleteProduct(int id){
        products.removeIf(product ->
                product.getId() == id
        );
    }

    public User getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    }
