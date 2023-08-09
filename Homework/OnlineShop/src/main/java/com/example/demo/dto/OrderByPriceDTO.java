package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderByPriceDTO {
    private Product product;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private List<OrderDataDTO> ordersWithMinPrice;
    private List<OrderDataDTO> ordersWithMaxPrice;
}
