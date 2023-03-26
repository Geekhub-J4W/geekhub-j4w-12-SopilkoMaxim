package edu.geekhub.product;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
public class Product {
    private int id;
    private String name="default";
    private int price;

    private int rating=0;

    private int quantityToOrder =0;

    private int quantityOnStock = 0;

    private byte[] imgBytes;


    public Product(int id, String name, int price, int rating, int quantity, byte[] imgBytes) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
        this.quantityOnStock=quantity;
        this.imgBytes=imgBytes;
    }

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
    public Product(int id, String name, int price, int rating, int quantityOnStock, MultipartFile file) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.quantityOnStock=quantityOnStock;
        try {
            this.imgBytes=file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(MultipartFile file) {
        try {
            this.imgBytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", quantityToOrder=" + quantityToOrder +
                ", quantityOnStock=" + quantityOnStock +
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
