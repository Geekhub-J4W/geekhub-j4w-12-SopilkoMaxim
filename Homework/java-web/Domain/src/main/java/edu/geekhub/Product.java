package edu.geekhub;

import java.util.Comparator;

public class Product {
    public static int COUNTER = 1;
    private int id;
    private String name;
    private int price;

    public Product(String name, int price) {
        this.id=COUNTER++;
        this.name = name;
        this.price = price;
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
            return o2.getName().compareTo(o1.getName());
        }
    };

}
