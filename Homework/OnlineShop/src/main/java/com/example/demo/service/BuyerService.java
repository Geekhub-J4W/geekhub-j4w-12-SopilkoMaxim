package com.example.demo.service;

import com.example.demo.daoimpl.BuyerDao;
import com.example.demo.dto.BuyerSpendingDTO;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BuyerService implements BuyerDao {

    private static final Logger logger = LoggerFactory.getLogger(BuyerService.class);

    private final BuyerRepository buyerRepository;
    private final OrderRepository orderRepository;

    private final OrderService orderService;
    private final OrderItemRepository orderItemRepository;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;
    private final ProductRepository productRepository;


    @Autowired
    public BuyerService(BuyerRepository buyerRepository, OrderRepository orderRepository, OrderService orderService, OrderItemRepository orderItemRepository, ProductPriceHistoryRepository productPriceHistoryRepository, ProductRepository productRepository) {
        this.buyerRepository = buyerRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.orderItemRepository = orderItemRepository;
        this.productPriceHistoryRepository = productPriceHistoryRepository;
        this.productRepository = productRepository;
    }





    @Override
    public void add(Buyer buyer) {
        Validator.validateBuyer(buyer);
        logger.info("Adding buyer: {}", buyer);
        buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> getAll() {
        logger.info("Getting all buyers");
        return buyerRepository.findAll();
    }

    @Override
    public Buyer getById(Long id) {
        logger.info("Getting buyer by ID: {}", id);
        Optional<Buyer> optionalBuyer = buyerRepository.findById(id);

        return optionalBuyer.orElse(null);
    }

    @Override
    @Transactional
    public void remove(Buyer buyer) {
        logger.info("Removing buyer: {}", buyer);
        orderService.deleteByBuyerId(buyer);
        buyerRepository.delete(buyer);
    }

    @Override
    public void update(Buyer buyer) {
        Validator.validateBuyer(buyer);
        logger.info("Updating buyer: {}", buyer);
        Buyer existingBuyer = buyerRepository.getById(buyer.getId());
        if (existingBuyer != null) {
            buyerRepository.save(buyer);
        } else {
            throw new IllegalArgumentException("Buyer with ID " + buyer.getId() + " not found.");
        }
    }
    public BuyerSpendingDTO getBuyerSpendingAndOrdersByQuarter(long buyerId, LocalDate startDate, LocalDate endDate) {
        Buyer buyer = buyerRepository.findById(buyerId).orElse(null);
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer with ID " + buyerId + " not found.");
        }

        List<Order> orders = orderRepository.findByBuyerAndDateOfPurchaseBetween(buyer, startDate, endDate);
        BigDecimal totalSpending = calculateTotalSpending(orders);

        Map<String, Integer> ordersByQuarter = calculateOrdersByQuarter(orders);

        return new BuyerSpendingDTO(
                buyer.getFirstName(),
                buyer.getLastName(),
                totalSpending,
                ordersByQuarter,
                orders
        );
    }

    public BigDecimal calculateTotalSpending(List<Order> orders) {
        BigDecimal totalSpending = BigDecimal.ZERO;

        for (Order order : orders) {
            for (OrderItem item : orderItemRepository.findAllByOrderId(order.getId())) {
                BigDecimal productPrice = getProductPrice(item.getProduct(), order.getDateOfPurchase().toLocalDate());
                totalSpending = totalSpending.add(productPrice.multiply(BigDecimal.valueOf(item.getQuantity())));
            }
        }

        return totalSpending;
    }

    private BigDecimal getProductPrice(Product product, LocalDate date) {
        Optional<BigDecimal> price = productPriceHistoryRepository.findPriceByProductAndDateStartBeforeOrderByDateStartDesc(product, Date.valueOf(date));
        if(price.isPresent())
            return price.get();
        return product.getRegularPrice();


    }

    public Map<String, Integer> calculateOrdersByQuarter(List<Order> orders) {
        Map<String, Integer> ordersByQuarter = new HashMap<>();
        for (Order order : orders) {
            String quarter = getQuarter(order.getDateOfPurchase().toLocalDate());
            ordersByQuarter.put(quarter, ordersByQuarter.getOrDefault(quarter, 0) + 1);
        }
        return ordersByQuarter;
    }

    private String getQuarter(LocalDate date) {
        int quarterNumber = (date.getMonthValue() - 1) / 3 + 1;
        Year year = Year.of(date.getYear());
        return "Q" + quarterNumber + " " + year;
    }

    public  void deleteAll(){
        productPriceHistoryRepository.deleteAll();
        orderItemRepository.deleteAll();
        orderRepository.deleteAll();
        buyerRepository.deleteAll();
        productRepository.deleteAll();
    }
}
