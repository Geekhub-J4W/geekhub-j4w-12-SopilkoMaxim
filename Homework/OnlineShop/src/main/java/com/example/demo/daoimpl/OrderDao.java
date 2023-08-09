package com.example.demo.daoimpl;


import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;

import java.util.List;

public interface OrderDao {
    void add(Order order, List<OrderItem> orderItems);

    List<Order> getAll();

    Order getById(Long id);

    void update(Order order, List<OrderItem> orderItems);

    void remove(Order order);
}
