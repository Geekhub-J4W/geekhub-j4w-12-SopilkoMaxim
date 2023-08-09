package com.example.demo.dto;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Order order;
    private List<OrderItem> orderItems;
    private String deliveryMethod;

}
