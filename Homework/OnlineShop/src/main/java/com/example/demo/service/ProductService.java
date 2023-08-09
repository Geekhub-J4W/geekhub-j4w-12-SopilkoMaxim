package com.example.demo.service;

import com.example.demo.daoimpl.ProductDao;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductPriceHistory;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductPriceHistoryRepository;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, ProductPriceHistoryRepository productPriceHistoryRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.productPriceHistoryRepository = productPriceHistoryRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }







    @Override
    public void add(Product product) {
        Validator.validateProduct(product);
        logger.info("Adding product: {}", product);

        productRepository.save(product);

        ProductPriceHistory productPriceHistory = new ProductPriceHistory(product, product.getRegularPrice(), product.getDateAdd(), null, 0);
        productPriceHistoryRepository.save(productPriceHistory);
    }

    @Override
    public List<Product> getAll() {
        logger.info("Getting all products");
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long id) {
        logger.info("Getting product by ID: {}", id);
        return productRepository.findById(id).get();
    }

    @Override
    public void update(Product product) {
        logger.info("Updating product: {}", product);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void remove(Product product) {
        logger.info("Removing product and related data: {}", product);

        List<ProductPriceHistory> priceHistoryList = productPriceHistoryRepository.findByProductId(product.getId());
        productPriceHistoryRepository.deleteAll(priceHistoryList);

        List<OrderItem> items = orderItemRepository.findAllByProductId(product.getId());
        orderItemRepository.deleteAll(items);

        productRepository.delete(product);
    }
    public void addProductPriceHistory(ProductPriceHistory productPriceHistory) {
        Validator.validateProductPriceHistory(productPriceHistory);
        logger.info("Add product price history: {}", productPriceHistory);
        productPriceHistoryRepository.save(productPriceHistory);
    }

    public List<ProductPriceHistory> getProductPriceHistoryByProductId(Long productId) {
        return productPriceHistoryRepository.findByProductId(productId);
    }

    public void updateProductPrice(ProductPriceHistory productPriceHistory) {
        logger.info("Adding discount to product: {}", productPriceHistory.getProduct());
        Product product = productPriceHistory.getProduct();
        ProductPriceHistory regularPriceHistory = productPriceHistoryRepository.findLatestByProductId(product.getId());
        regularPriceHistory.setDateEnd(productPriceHistory.getDateStart());
        productPriceHistoryRepository.saveAndFlush(regularPriceHistory);
        productPriceHistoryRepository.save(productPriceHistory);
    }
}
