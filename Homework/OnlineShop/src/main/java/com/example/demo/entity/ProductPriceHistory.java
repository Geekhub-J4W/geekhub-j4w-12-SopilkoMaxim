package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product_price_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class ProductPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "date_start", nullable = false)
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "discount", nullable = false)
    private int discount;

    public ProductPriceHistory(Product product, BigDecimal price, Date dateStart, Date dateEnd, int discount) {
        this.product = product;
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.discount = discount;
    }
}
