package edu.geekhub.bucket;

import edu.geekhub.customer.CustomerService;
import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class BucketController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Bucket bucket;

    public void setCustomer(int id) {
        bucket.setCustomer(customerService.getCustomerById(id));
    }

    public void setCustomer(String name) {
        bucket.setCustomer(customerService.getCustomerByName(name));
    }

    public boolean addProductWithQuanity(int id, int quantity) {
        Product addproduct = productService.getProductById(id);
        if (addproduct.getQuantityOnStock() < quantity) {
            System.out.println("Insufficient quantity in stock. Select another quantity");
            return false;
        } else {
            addproduct.setQuantityOnStock(addproduct.getQuantityOnStock() - quantity);
            addproduct.setQuantityToOrder(quantity);
            bucket.addProduct(addproduct);
            return true;
        }
    }

    public void deleteProduct(int id) {
        bucket.deleteProduct(id);
    }

    public void showProductsInBucket() {
        System.out.println(bucket.getProducts());
    }

    public void createOrder() {
        if (bucket.getCustomer() == null) {
            System.out.println("You need to set customer id");

        } else {
            LocalDateTime date = LocalDateTime.now();
            List<Order> order = new ArrayList<>();
            for (Product product : bucket.getProducts()) {
                orderRepository.addOrder(new Order(product.getQuantityToOrder(),
                        date,bucket.getCustomer().getId(),product.getId()));
            }
            createOrderFile();
        }
    }
    private void createOrderFile() {
        String fileName="Homework/java-web/Domain/src/orders/Order"+ LocalDateTime.now().toString().replace(".","").replace(":","") +".txt";

        try {

            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Order for customer:"+bucket.getCustomer().getName()+" with age:"
                    +bucket.getCustomer().getAge()+":\n");
            int totalPrice=0;
            for (Product product : bucket.getProducts()) {
                fileWriter.write(product.getName()+" : price:"+product.getPrice()+"\n" + " quantity:"+product.getQuantityToOrder());
                totalPrice+=product.getPrice()*product.getQuantityToOrder();
            }
            fileWriter.write("Total price:"+totalPrice+ "\n\n\n");
            fileWriter.write("Order was created:"+LocalDateTime.now()+"   Some company, Stamp");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
