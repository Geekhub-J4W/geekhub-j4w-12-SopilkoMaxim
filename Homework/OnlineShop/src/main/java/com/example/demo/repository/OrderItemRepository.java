package com.example.demo.repository;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    //@Query("DELETE FROM OrderItem oi WHERE oi.order.id = :id")
    void deleteOrderItemsByOrderId(long id);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId")
    List<OrderItem> findAllByOrderId(@Param("orderId") long orderId);
    @Query("SELECT oi FROM OrderItem oi " +
            "JOIN oi.product p " +
            "WHERE p = :product AND oi.priceByDate = :price")
    List<OrderItem> findByProductAndPrice(@Param("product") Product product, @Param("price") BigDecimal price);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.product.id = :id")
    List<OrderItem> findAllByProductId(long id);
}
