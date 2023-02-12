package edu.geekhub;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProductById(int id){
        for (Product product: products) {
            if(product.getId()==id)
                return product;
        }
        return null;
    }

    public void deleteProduct(int id) {
        products.removeIf(product ->
                product.getId() == id
        );
    }

    public List<Product> getProducts() {
        return products;
    }
}
