package com.example.demo.daoimpl;


import com.example.demo.entity.Product;

import java.util.List;

public interface ProductDao {
    void add(Product product);

    List<Product> getAll();

    Product getById(Long id);

    void update(Product product);

    void remove(Product product);
}
