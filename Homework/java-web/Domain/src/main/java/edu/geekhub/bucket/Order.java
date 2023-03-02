package edu.geekhub.bucket;

import edu.geekhub.customer.Customer;
import edu.geekhub.product.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int quantity_products;

    private LocalDateTime date;
    private int id_customer;
    private int id_product;

    public Order(int quantity_products, LocalDateTime date, int id_customer, int id_product) {
        this.quantity_products = quantity_products;
        this.date = date;
        this.id_customer = id_customer;
        this.id_product = id_product;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getQuantity_products() {
        return quantity_products;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getId_customer() {
        return id_customer;
    }

    public int getId_product() {
        return id_product;
    }
}
