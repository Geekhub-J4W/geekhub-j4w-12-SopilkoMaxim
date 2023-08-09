package com.example.demo.controller;

import com.example.demo.dto.OrderByPriceDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.UpdateOrderRequestDTO;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderDTO orderDTO) {
        orderService.add(orderDTO.getOrder(), orderDTO.getOrderItems());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO orderDTO = orderService.getOrderDTOById(id);
        if (orderDTO != null) {
            return ResponseEntity.ok(orderDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable Long id,
            @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO
    ) {
        Order existingOrder = orderService.getById(id);
        if (existingOrder != null) {
            Order updatedOrder = updateOrderRequestDTO.getOrder();
            List<OrderItem> orderItems = updateOrderRequestDTO.getOrderItems();

            updatedOrder.setId(id);
            orderService.update(updatedOrder, orderItems);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order != null) {
            orderService.remove(order);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-min-max-price")
    public List<OrderByPriceDTO> findOrdersByMinMaxPrice() {
        return orderService.findOrdersByMinMaxPrice();
    }
}



