package edu.geekhub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    public ProductService productService;

    @BeforeEach
    void setUp() {
        productService=new ProductService();
        productService.addProduct(new Product("First",50));
        productService.addProduct(new Product("Second",40));
        productService.addProduct(new Product("Third",30));
        productService.addProduct(new Product("Fourth",60));
        productService.addProduct(new Product("Fifth",70));
    }

    @Test
    public void addProduct(){
        productService.addProduct(new Product("Six",70));

        assertEquals(6,productService.getProducts().size());

    }

    @Test
    public void deleteProduct(){
        productService.deleteProduct(1);

        assertEquals(4,productService.getProducts().size());
    }

    @Test
    public void sortListByPrice(){
        var sortedList = productService.sortedListByPrice();

        assertEquals(30,sortedList.get(0).getPrice());
    }

    @Test
    public void sortListByName(){
        productService.addProduct(new Product("Aaaa",40));
        var sortedList = productService.sortedListByPrice();

        assertEquals("Aaaa",sortedList.get(0).getName());
    }
}