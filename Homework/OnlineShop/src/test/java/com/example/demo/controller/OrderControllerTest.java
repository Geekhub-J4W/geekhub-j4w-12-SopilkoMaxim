package com.example.demo.controller;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.UpdateOrderRequestDTO;
import com.example.demo.entity.Buyer;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.service.BuyerService;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@Sql(value = {"/load_data_to_test_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete_all.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private OrderService orderService;

    @Test
    void addOrder() throws Exception {
        Order order = new Order();
        order.setBuyer(buyerService.getById(301L));
        order.setAddress("Asgard");
        order.setDeliveryMethod("Standard Delivery");
        order.setDateOfPurchase(Date.valueOf(LocalDate.now()));

        List<OrderItem> orderItems = new ArrayList<>();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(order);
        orderDTO.setOrderItems(orderItems);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post("/order")
                        .content(TestUtils.asJsonString(orderDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    void getAllOrders() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/order"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(status().isOk())
                .andReturn();
    }


    @Test
    void getOrderById() throws Exception {
        long orderId = 401L;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/order/" + orderId))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        OrderDTO orderDTO = new ObjectMapper().readValue(responseContent, OrderDTO.class);

        assertEquals(orderId, orderDTO.getOrder().getId());
        assertEquals("Asgard", orderDTO.getOrder().getAddress());
    }


    @Test
    @Transactional
    void updateOrder() throws Exception {
        long orderId = 401L;

        Buyer buyer = new Buyer();
        buyer.setId(301L);
        buyer.setFirstName("Thor");
        buyer.setLastName("Odinson");
        buyer.setAddress("Asgard");

        Order updatedOrder = new Order();
        updatedOrder.setBuyer(buyer);
        updatedOrder.setAddress("New Address");
        updatedOrder.setDateOfPurchase(Date.valueOf(LocalDate.now()));
        updatedOrder.setDeliveryMethod("Super fast");

        List<OrderItem> updatedOrderItems = orderService.getAllOrderItemByOrderId(orderId);

        UpdateOrderRequestDTO updateOrderRequestDTO = new UpdateOrderRequestDTO();
        updateOrderRequestDTO.setOrder(updatedOrder);
        updateOrderRequestDTO.setOrderItems(updatedOrderItems);

        MvcResult mvcResult = mockMvc.perform(put("/order/" + orderId)
                        .content(TestUtils.asJsonString(updateOrderRequestDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        Order fetchedOrder = orderService.getById(orderId);
        assertNotNull(fetchedOrder);
        assertEquals(updatedOrder.getAddress(), fetchedOrder.getAddress());
        assertEquals(updatedOrder.getDeliveryMethod(), fetchedOrder.getDeliveryMethod());

    }



    @Test
    void deleteOrder() throws Exception {
        long orderId = 401L;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/order/" + orderId))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void findOrdersByMinMaxPrice() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/order/by-min-max-price"))
                .andExpect(status().isOk())
                .andReturn();

    }

}
