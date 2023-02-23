package edu.geekhub.bucket;

import edu.geekhub.customer.Customer;
import edu.geekhub.customer.CustomerService;
import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@Scope("prototype")
public class Bucket {
    private Customer customer;
    private List<Product> products = new ArrayList<>();

    public void setCustomer(Customer customer)
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

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    }
