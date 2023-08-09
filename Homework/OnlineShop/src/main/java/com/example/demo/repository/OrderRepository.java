package com.example.demo.repository;

import com.example.demo.entity.Buyer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o " +
            "WHERE o.buyer = :buyer " +
            "AND o.dateOfPurchase BETWEEN :startDate AND :endDate")
    List<Order> findByBuyerAndDateOfPurchaseBetween(@Param("buyer") Buyer buyer,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

    @Query("DELETE FROM Order o WHERE o.buyer = :buyer")
    void deleteByBuyerId(Buyer buyer);

    @Query("SELECT o FROM Order o WHERE o.buyer = :buyer")
    List<Order> getAllOrdersByBuyerId(Buyer buyer);

}
