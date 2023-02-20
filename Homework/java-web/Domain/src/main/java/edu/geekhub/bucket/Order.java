package edu.geekhub.bucket;

import edu.geekhub.customer.Customer;
import edu.geekhub.product.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private Customer customer;
    private List<Product> products;
    private String fileName;

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }
    public void createOrder(){
        fileName="Order"+ LocalDateTime.now().toString().replace(".","") +".txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("Order for customer:"+customer.getName()+" with age:"
                    +customer.getAge()+":\n");
            int totalPrice=0;
            for (Product product : products) {
                fileWriter.write(product.getName()+" : "+product.getPrice()+"\n");
                totalPrice+=product.getPrice();
            }
            fileWriter.write("Total price:"+totalPrice+ "\n\n\n");
            fileWriter.write("Order was created:"+LocalDateTime.now()+"   Some company, Stamp");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
