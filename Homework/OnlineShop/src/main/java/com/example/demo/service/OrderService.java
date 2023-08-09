package com.example.demo.service;

import com.example.demo.daoimpl.OrderDao;
import com.example.demo.dto.OrderByPriceDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDataDTO;
import com.example.demo.entity.Buyer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderService implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderItemRepository orderItemRepository, ProductRepository productRepository, ProductPriceHistoryRepository productPriceHistoryRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.productPriceHistoryRepository = productPriceHistoryRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void add(Order order, List<OrderItem> orderItems) {
        Validator.validateOrder(order);
        for (OrderItem orderItem : orderItems) {
            Validator.validateOrderItem(orderItem);
        }
        logger.info("Adding order: {}", order);
        orderRepository.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItem.setPriceByDate(productPriceHistoryRepository.findPriceByIdAndDate(orderItem.getProduct().getId(),order.getDateOfPurchase()));
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public List<Order> getAll() {
        logger.info("Getting all orders");
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        logger.info("Getting order by ID: {}", id);
        return orderRepository.getById(id);
    }

    @Override
    public void update(Order order, List<OrderItem> orderItems) {
        Validator.validateOrder(order);
        for (OrderItem orderItem : orderItems) {
            Validator.validateOrderItem(orderItem);
        }
        logger.info("Updating order: {}", order);
        Order existingOrder = orderRepository.findById(order.getId()).orElse(null);
        if (existingOrder != null) {
            existingOrder.setBuyer(order.getBuyer());
            existingOrder.setAddress(order.getAddress());
            existingOrder.setDeliveryMethod(order.getDeliveryMethod());
            orderRepository.save(existingOrder);

            orderItemRepository.deleteOrderItemsByOrderId(existingOrder.getId());

            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(existingOrder);
                orderItemRepository.save(orderItem);
            }
        }
    }

    @Override
    public void remove(Order order) {
        logger.info("Removing order: {}", order);
        List<OrderItem> orderItemList = orderItemRepository.findAllByOrderId(order.getId());
        orderItemRepository.deleteAll(orderItemList);
        orderRepository.delete(order);
    }

    public List<OrderByPriceDTO> findOrdersByMinMaxPrice() {
        List<OrderByPriceDTO> result = new ArrayList<>();

        List<Product> allProducts = productRepository.findAll();
        for (Product product : allProducts) {
            Optional<BigDecimal> minPrice = productPriceHistoryRepository.findByProductOrderByPriceAsc(product);
            Optional<BigDecimal> maxPrice = productPriceHistoryRepository.findByProductOrderByPriceDesc(product);

            if (minPrice.isPresent() && maxPrice.isPresent()) {
                List<OrderItem> minPriceOrderItems = orderItemRepository.findByProductAndPrice(product, minPrice.get());
                List<OrderItem> maxPriceOrderItems = orderItemRepository.findByProductAndPrice(product, maxPrice.get());

                OrderByPriceDTO orderByPriceDTO = new OrderByPriceDTO();
                orderByPriceDTO.setProduct(product);
                orderByPriceDTO.setMinPrice(minPrice.get());
                orderByPriceDTO.setMaxPrice(maxPrice.get());
                orderByPriceDTO.setOrdersWithMinPrice(getOrdersData(minPriceOrderItems));
                orderByPriceDTO.setOrdersWithMaxPrice(getOrdersData(maxPriceOrderItems));

                result.add(orderByPriceDTO);
            }
        }

        return result;
    }

    private List<OrderDataDTO> getOrdersData(List<OrderItem> orderItems) {
        List<OrderDataDTO> ordersData = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            Order order = orderItem.getOrder();
            Buyer buyer = order.getBuyer();

            OrderDataDTO orderDataDTO = new OrderDataDTO();
            orderDataDTO.setOrderId(order.getId());
            orderDataDTO.setPrice(orderItem.getPriceByDate());
            orderDataDTO.setBuyerName(buyer.getFirstName() + " " + buyer.getLastName());

            ordersData.add(orderDataDTO);
        }

        return ordersData;
    }

    @Transactional
    public void deleteByBuyerId(Buyer buyer) {
        List<Order> orders = orderRepository.getAllOrdersByBuyerId(buyer);
        for (Order order:orders) {
            remove(order);
        }
    }

    public OrderDTO getOrderDTOById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            OrderDTO orderDTO = convertToDTO(order);
            return orderDTO;
        } else {
            return null;
        }
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(order);
        orderDTO.setDeliveryMethod(order.getDeliveryMethod());
        return orderDTO;
    }

    public List<OrderItem> getAllOrderItemByOrderId(Long id) {
        return orderItemRepository.findAllByOrderId(id);
    }
}
