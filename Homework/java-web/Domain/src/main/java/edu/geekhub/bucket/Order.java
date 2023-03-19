package edu.geekhub.bucket;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

    public Order(int id, int quantityProducts, Date date, int idCustomer, int idProduct) {
        this.id = id;
        this.quantity_products = quantityProducts;
        this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.id_customer = idCustomer;
        this.id_product = idProduct;
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

    public Order() {
    }

    public void setQuantity_products(int quantity_products) {
        this.quantity_products = quantity_products;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}
