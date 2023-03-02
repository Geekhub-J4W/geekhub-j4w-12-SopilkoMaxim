package edu.geekhub.product;

import java.util.Comparator;


public class Product {
    private int id;
    private String name;
    private int price;

    private int rating=0;

    private int quantityToOrder =0;

    private int quantityOnStock = 0;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, int price, int rating, int quantityOnStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.quantityOnStock=quantityOnStock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getQuantityToOrder() {
        return quantityToOrder;
    }


    public void setQuantityToOrder(int quantityToOrder) {
        this.quantityToOrder = quantityToOrder;
    }
    public int getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(int quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
    public static final Comparator<Product> COMPARE_BY_PRICE = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getPrice()-o2.getPrice();
        }
    };
    public static final Comparator<Product> COMPARE_BY_NAME = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

}
