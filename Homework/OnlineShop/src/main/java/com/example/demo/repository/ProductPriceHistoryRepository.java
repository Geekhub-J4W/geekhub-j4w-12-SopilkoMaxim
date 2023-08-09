package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory,Long> {
    @Query("SELECT pph FROM ProductPriceHistory pph " +
            "WHERE pph.product.id = :productId")
    List<ProductPriceHistory> findByProductId(@Param("productId") Long productId);

    @Query("SELECT pph FROM ProductPriceHistory pph " +
            "WHERE pph.product.id = :id " +
            "ORDER BY pph.dateEnd DESC")
    ProductPriceHistory findLatestByProductId(@Param("id") long id);

    @Query("SELECT p.price FROM ProductPriceHistory p " +
            "WHERE p.product = :product AND p.dateStart <= :date " +
            "ORDER BY p.dateStart DESC LIMIT 1")
    Optional<BigDecimal> findPriceByProductAndDateStartBeforeOrderByDateStartDesc(@Param("product") Product product,
                                                                        @Param("date") Date date);

    @Query("SELECT MIN(pph.price) FROM ProductPriceHistory pph " +
            "WHERE pph.product = :product")
    Optional<BigDecimal> findByProductOrderByPriceAsc(@Param("product") Product product);

    @Query("SELECT MAX(pph.price) FROM ProductPriceHistory pph " +
            "WHERE pph.product = :product")
    Optional<BigDecimal> findByProductOrderByPriceDesc(@Param("product") Product product);

    @Query("SELECT p.price FROM ProductPriceHistory p " +
            "WHERE p.product.id = :id " +
            "AND p.dateStart <= :dateOfPurchase " +
            "ORDER BY p.dateStart DESC " +
            "LIMIT 1")
    BigDecimal findPriceByIdAndDate(@Param("id") long id, @Param("dateOfPurchase") Date dateOfPurchase);
}
