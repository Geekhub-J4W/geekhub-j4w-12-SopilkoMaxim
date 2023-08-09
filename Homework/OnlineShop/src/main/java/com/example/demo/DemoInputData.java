package com.example.demo;

import com.example.demo.dto.BuyerSpendingDTO;
import com.example.demo.dto.OrderByPriceDTO;
import com.example.demo.entity.*;
import com.example.demo.service.BuyerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DemoInputData {

    private final BuyerService buyerService;

    private final OrderService orderService;

    private final ProductService productService;

    @Autowired
    public DemoInputData(BuyerService buyerService, OrderService orderService, ProductService productService) {
        this.buyerService = buyerService;
        this.orderService = orderService;
        this.productService = productService;
    }

    public void save() {
        Buyer buyer = Buyer.builder().firstName("Tor")
                .lastName("Odenson")
                .address("Ragnarok")
                .id(1L).build();
        Buyer buyer1 = Buyer.builder().firstName("Loky")
                .lastName("Odenson")
                .address("Ragnarok")
                .id(2L).build();
        Buyer buyer2 = Buyer.builder().firstName("Tony")
                .lastName("Stark")
                .address("New York")
                .id(3L).build();
        Buyer buyer3 = Buyer.builder().firstName("Hulk")
                .lastName("Benner")
                .address("Washington")
                .id(4L).build();
        Buyer buyer4 = Buyer.builder().firstName("Stive")
                .lastName("Rogers")
                .address("New York")
                .id(5L).build();
        buyerService.add(buyer);
        buyerService.add(buyer1);
        buyerService.add(buyer2);
        buyerService.add(buyer3);
        buyerService.add(buyer4);
        Product product = Product.builder()
                .id(1L)
                .name("Iphone 14")
                .dateAdd(Date.valueOf(LocalDateTime.now().minusMonths(1).toLocalDate()))
                .regularPrice(BigDecimal.valueOf(1000))
                .manufacturer("Apple")
                .build();
        Product product1 = Product.builder()
                .id(2L)
                .name("Iphone 15")
                .dateAdd(Date.valueOf(LocalDateTime.now().minusMonths(2).toLocalDate()))
                .regularPrice(BigDecimal.valueOf(1200))
                .manufacturer("Apple")
                .build();
        Product product2 = Product.builder()
                .id(3L)
                .name("Samsung s20")
                .dateAdd(Date.valueOf(LocalDateTime.now().minusMonths(1).toLocalDate()))
                .regularPrice(BigDecimal.valueOf(1000))
                .manufacturer("Samsung")
                .build();
        Product product3 = Product.builder()
                .id(4L)
                .name("Htc One")
                .dateAdd(Date.valueOf(LocalDateTime.now().minusMonths(2).toLocalDate()))
                .regularPrice(BigDecimal.valueOf(500))
                .manufacturer("Htc")
                .build();
        Product product4 = Product.builder()
                .id(5L)
                .name("Nokia 3310")
                .dateAdd(Date.valueOf(LocalDateTime.now().minusMonths(1).toLocalDate()))
                .regularPrice(BigDecimal.valueOf(100))
                .manufacturer("Nokia")
                .build();
        productService.add(product);
        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);

        ProductPriceHistory productPriceHistory = ProductPriceHistory.builder()
                .id(6L)
                .product(product)
                .dateStart(Date.valueOf(LocalDateTime.now().minusWeeks(3).toLocalDate()))
                .dateEnd(Date.valueOf(LocalDateTime.now().minusWeeks(2).toLocalDate()))
                .price(BigDecimal.valueOf(900))
                .discount(10)
                .build();
        ProductPriceHistory productPriceHistory1 = ProductPriceHistory.builder()
                .id(7L)
                .product(product1)
                .dateStart(Date.valueOf(LocalDateTime.now().minusWeeks(3).toLocalDate()))
                .dateEnd(Date.valueOf(LocalDateTime.now().minusWeeks(2).toLocalDate()))
                .price(BigDecimal.valueOf(1000))
                .discount(10)
                .build();
        ProductPriceHistory productPriceHistory2 = ProductPriceHistory.builder()
                .id(8L)
                .product(product2)
                .dateStart(Date.valueOf(LocalDateTime.now().minusWeeks(4).toLocalDate()))
                .dateEnd(Date.valueOf(LocalDateTime.now().minusWeeks(2).toLocalDate()))
                .price(BigDecimal.valueOf(800))
                .discount(10)
                .build();
        ProductPriceHistory productPriceHistory3 = ProductPriceHistory.builder()
                .id(9L)
                .product(product3)
                .dateStart(Date.valueOf(LocalDateTime.now().minusWeeks(3).toLocalDate()))
                .dateEnd(Date.valueOf(LocalDateTime.now().minusWeeks(1).toLocalDate()))
                .price(BigDecimal.valueOf(400))
                .discount(10)
                .build();
        ProductPriceHistory productPriceHistory4 = ProductPriceHistory.builder()
                .id(10L)
                .product(product4)
                .dateStart(Date.valueOf(LocalDateTime.now().minusWeeks(3).toLocalDate()))
                .dateEnd(Date.valueOf(LocalDateTime.now().minusWeeks(2).toLocalDate()))
                .price(BigDecimal.valueOf(90))
                .discount(10)
                .build();

        productService.updateProductPrice(productPriceHistory);
        productService.updateProductPrice(productPriceHistory1);
        productService.updateProductPrice(productPriceHistory2);
        productService.updateProductPrice(productPriceHistory3);
        productService.updateProductPrice(productPriceHistory4);

        Order order = Order.builder()
                .id(1L)
                .dateOfPurchase(Date.valueOf(LocalDateTime.now().minusWeeks(2).toLocalDate()))
                .buyer(buyer)
                .address(buyer.getAddress())
                .deliveryMethod("Ship")
                .build();

        Order order1 = Order.builder()
                .id(2L)
                .dateOfPurchase(Date.valueOf(LocalDateTime.now().minusWeeks(1).toLocalDate()))
                .buyer(buyer1)
                .address(buyer1.getAddress())
                .deliveryMethod("Truck")
                .build();
        Order order2 = Order.builder()
                .id(3L)
                .dateOfPurchase(Date.valueOf(LocalDateTime.now().minusWeeks(3).toLocalDate()))
                .buyer(buyer2)
                .address(buyer2.getAddress())
                .deliveryMethod("Traine")
                .build();
        Order order3 = Order.builder()
                .id(4L)
                .dateOfPurchase(Date.valueOf(LocalDateTime.now().minusWeeks(1).toLocalDate()))
                .buyer(buyer3)
                .address(buyer3.getAddress())
                .deliveryMethod("Plane")
                .build();
        Order order4 = Order.builder()
                .id(5L)
                .dateOfPurchase(Date.valueOf(LocalDateTime.now().minusWeeks(4).toLocalDate()))
                .buyer(buyer4)
                .address(buyer4.getAddress())
                .deliveryMethod("Ship")
                .build();

        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .order(order)
                .product(product)
                .quantity(3)
                .build();
        OrderItem orderItem2 = OrderItem.builder()
                .id(2L)
                .order(order)
                .product(product2)
                .quantity(1)
                .build();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        orderItemList.add(orderItem2);
        orderService.add(order,orderItemList);


        OrderItem orderItem3 = OrderItem.builder()
                .id(3L)
                .order(order1)
                .product(product)
                .quantity(1)
                .build();
        OrderItem orderItem4 = OrderItem.builder()
                .id(4L)
                .order(order1)
                .product(product4)
                .quantity(5)
                .build();
        List<OrderItem> orderItemList1 = new ArrayList<>();
        orderItemList1.add(orderItem3);
        orderItemList1.add(orderItem4);
        orderService.add(order1,orderItemList1);

        OrderItem orderItem5 = OrderItem.builder()
                .id(5L)
                .order(order2)
                .product(product4)
                .quantity(3)
                .build();
        OrderItem orderItem6 = OrderItem.builder()
                .id(6L)
                .order(order2)
                .product(product2)
                .quantity(2)
                .build();
        List<OrderItem> orderItemList2 = new ArrayList<>();
        orderItemList2.add(orderItem5);
        orderItemList2.add(orderItem6);
        orderService.add(order2,orderItemList2);

        OrderItem orderItem7 = OrderItem.builder()
                .id(7L)
                .order(order3)
                .product(product4)
                .quantity(1)
                .build();
        OrderItem orderItem8 = OrderItem.builder()
                .id(8L)
                .order(order3)
                .product(product1)
                .quantity(2)
                .build();
        List<OrderItem> orderItemList3 = new ArrayList<>();
        orderItemList3.add(orderItem7);
        orderItemList3.add(orderItem8);
        orderService.add(order3,orderItemList3);

        OrderItem orderItem9 = OrderItem.builder()
                .id(9L)
                .order(order4)
                .product(product)
                .quantity(3)
                .build();
        OrderItem orderItem10 = OrderItem.builder()
                .id(10L)
                .order(order4)
                .product(product2)
                .quantity(5)
                .build();
        List<OrderItem> orderItemList4 = new ArrayList<>();
        orderItemList4.add(orderItem9);
        orderItemList4.add(orderItem10);
        orderService.add(order4,orderItemList4);

    }

    public BuyerSpendingDTO getBuyerSpendingAndOrdersByQuarter(long buyerId, LocalDate startDate, LocalDate endDate){
        return buyerService.getBuyerSpendingAndOrdersByQuarter(buyerId,startDate,endDate);
    }

    public List<OrderByPriceDTO> findOrdersByMinMaxPrice(){
        return orderService.findOrdersByMinMaxPrice();
    }

    public void deleteAllData(){
        buyerService.deleteAll();
    }
}
