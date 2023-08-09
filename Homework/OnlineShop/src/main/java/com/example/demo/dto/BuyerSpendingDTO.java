package com.example.demo.dto;

import com.example.demo.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerSpendingDTO {
    private String firstName;
    private String lastName;
    private BigDecimal totalSpending;
    private Map<String, Integer> ordersByQuarter;
    private List<Order> orders;
}
