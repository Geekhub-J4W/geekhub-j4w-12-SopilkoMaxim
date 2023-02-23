package edu.geekhub.bucket;

import edu.geekhub.customer.Customer;
import edu.geekhub.customer.CustomerService;
import edu.geekhub.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BucketController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private Bucket bucket;

    public void setCustomer(int id) {
        bucket.setCustomer(customerService.getCustomerById(id));
    }

    public void setCustomer(String name) {
        bucket.setCustomer(customerService.getCustomerByName(name));
    }

    public void addProduct(int id) {
        bucket.addProduct(productService.getProductById(id));
    }

    public void deleteProduct(int id) {
        bucket.deleteProduct(id);
    }

    public void showProductsInBucket() {
        System.out.println(bucket.getProducts());
    }

    public void createOrder() {
        if(bucket.getCustomer()==null)
        {
            System.out.println("You need to set customer id");

        }
        else{
        Order order = new Order(bucket.getCustomer(), bucket.getProducts());
        order.createOrder();}
    }

}
