package com.example.demo.service;

import com.example.demo.entity.*;

import java.math.BigDecimal;

public class Validator {

    public static void validateBuyer(Buyer buyer) {
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer must not be null");
        }
        requireNonEmpty(buyer.getFirstName(), "First name must not be empty");
        requireNonEmpty(buyer.getLastName(), "Last name must not be empty");
        requireNonEmpty(buyer.getAddress(), "Address must not be empty");
    }

    public static void validateOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order must not be null");
        }
        requireNonNull(order.getBuyer(), "Order must have a buyer");
        requireNonEmpty(order.getAddress(), "Order address must not be empty");
        requireNonEmpty(order.getDeliveryMethod(), "Delivery method must not be empty");
        requireNonNull(order.getDateOfPurchase(), "Date of purchase must not be null");
    }

    public static void validateOrderItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("OrderItem must not be null");
        }
        requireNonNull(orderItem.getOrder(), "OrderItem must belong to an order");
        requireNonNull(orderItem.getProduct(), "OrderItem must have a product");
        requirePositive(orderItem.getQuantity(), "OrderItem quantity must be a positive integer");
    }

    public static void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        requireNonEmpty(product.getName(), "Product name must not be empty");
        requireNonEmpty(product.getManufacturer(), "Product manufacturer must not be empty");
        requireNonNull(product.getDateAdd(), "Product dateAdd must not be null");
        requireNonNull(product.getRegularPrice(), "Product currentPrice must not be null");
        requirePositive(product.getRegularPrice().compareTo(BigDecimal.ZERO), "Product currentPrice must be greater than zero");
    }

    public static void validateProductPriceHistory(ProductPriceHistory priceHistory) {
        if (priceHistory == null) {
            throw new IllegalArgumentException("ProductPriceHistory must not be null");
        }
        requireNonNull(priceHistory.getProduct(), "ProductPriceHistory must have a product");
        requireNonNull(priceHistory.getPrice(), "ProductPriceHistory price must not be null");
        requireNonNull(priceHistory.getDateStart(), "ProductPriceHistory dateStart must not be null");
        requirePositive(priceHistory.getPrice().compareTo(BigDecimal.ZERO), "ProductPriceHistory price must be greater than zero");
    }

    private static void requireNonNull(Object obj, String errorMessage) {
        if (obj == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void requireNonEmpty(String str, String errorMessage) {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void requirePositive(int value, String errorMessage) {
        if (value <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
