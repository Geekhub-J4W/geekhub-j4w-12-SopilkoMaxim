package com.example.demo.daoimpl;

import com.example.demo.entity.Buyer;

import java.util.List;

public interface BuyerDao {
    void add(Buyer buyer);

    List<Buyer> getAll();

    Buyer getById(Long id);

    void update(Buyer buyer);

    void remove(Buyer buyer);

}
