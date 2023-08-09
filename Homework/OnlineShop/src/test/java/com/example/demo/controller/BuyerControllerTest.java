package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@Sql(value = {"/load_data_to_test_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete_all.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void getAllBuyers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/buyer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    void getBuyerById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/buyer/{id}", 301))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(301))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Thor"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Odinson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Asgard"))
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/buyer/{id}", 100))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    void deleteBuyer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/buyer/{id}", 301))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/buyer/{id}", 100))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateBuyer() throws Exception {
        String updatedBuyerJson = "{\"id\": 301, \"firstName\": \"Thor\", \"lastName\": \"Odinson\", \"address\": \"Asgard\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/buyer/{id}", 301)
                        .contentType("application/json")
                        .content(updatedBuyerJson))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print()); // Print response for debugging

        mockMvc.perform(MockMvcRequestBuilders.put("/api/buyer/{id}", 100)
                        .contentType("application/json")
                        .content(updatedBuyerJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(print());
    }

    @Test
    void getBuyerSpendingAndOrdersByQuarter() throws Exception {
        String expectedFirstName = "Thor";
        String expectedLastName = "Odinson";
        BigDecimal expectedTotalSpending = new BigDecimal("240.0");
        Map<String, Integer> expectedOrdersByQuarter = new HashMap<>();
        expectedOrdersByQuarter.put("Q1 2023", 1);

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 31);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/buyer/spending")
                        .param("buyerId", "301")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(expectedFirstName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(expectedLastName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSpending").value(expectedTotalSpending))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ordersByQuarter").value(expectedOrdersByQuarter))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orders").exists())
                .andDo(print());
    }
}
