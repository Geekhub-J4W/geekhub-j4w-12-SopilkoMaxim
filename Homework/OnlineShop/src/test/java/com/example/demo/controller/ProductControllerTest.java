package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductPriceHistory;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/applicationTest.properties")
@Sql(value = {"/load_data_to_test_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete_all.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;
    @Test
    void addProduct() throws Exception {
        Product newProduct = new Product();
        newProduct.setName("New Product");
        newProduct.setManufacturer("New Manufacturer");
        newProduct.setRegularPrice(BigDecimal.valueOf(300.00));
        newProduct.setDateAdd(Date.valueOf(LocalDate.now()));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product")
                        .content(TestUtils.asJsonString(newProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<Product> products = productService.getAll();
        assertThat(products, hasSize(4));
    }

    @Test
    void getAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getProductById() throws Exception {
        long productId = 101L;
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product A")));
    }

    @Test
    void updateProduct() throws Exception {
        long productId = 101L; // Assuming this ID exists
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setManufacturer("Updated Manufacturer");
        updatedProduct.setRegularPrice(BigDecimal.valueOf(250.00));
        updatedProduct.setDateAdd(Date.valueOf(LocalDate.now()));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/product/{id}", productId)
                        .content(TestUtils.asJsonString(updatedProduct))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Product retrievedProduct = productService.getById(productId);
        assertNotNull(retrievedProduct);
        assertEquals("Updated Product", retrievedProduct.getName());
        assertEquals(0, retrievedProduct.getRegularPrice().compareTo(BigDecimal.valueOf(250.00)));
    }

    @Test
    void deleteProduct() throws Exception {
        long productId = 101L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/product/{id}", productId))
                .andExpect(status().isNoContent());
        List<Product> products = productService.getAll();
        assertThat(products, hasSize(2));
    }

    @Test
    void addProductPriceHistory() throws Exception {
        long productId = 101L;
        ProductPriceHistory priceHistory = new ProductPriceHistory();
        priceHistory.setProduct(productService.getById(productId));
        priceHistory.setDateStart(Date.valueOf(LocalDate.now().minusMonths(2)));
        priceHistory.setDateEnd(Date.valueOf(LocalDate.now()));
        priceHistory.setPrice(BigDecimal.valueOf(90.00));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/product/{id}/price", productId)
                        .content(TestUtils.asJsonString(priceHistory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<ProductPriceHistory> priceHistoryList = productService.getProductPriceHistoryByProductId(productId);
        assertThat(priceHistoryList, hasSize(2));
    }

    @Test
    void getProductPriceHistoryByProductId() throws Exception {
        long productId = 101L;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/{id}/price", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    void updateProductPrice() throws Exception {
        long productId = 101L;
        ProductPriceHistory updatedPriceHistory = new ProductPriceHistory();
        updatedPriceHistory.setProduct(productService.getById(productId));
        updatedPriceHistory.setDateStart(Date.valueOf(LocalDate.now().minusWeeks(1)));
        updatedPriceHistory.setDateEnd(Date.valueOf(LocalDate.now()));
        updatedPriceHistory.setDiscount(10);
        updatedPriceHistory.setPrice(BigDecimal.valueOf(85.00));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/product/{id}/price", productId)
                        .content(TestUtils.asJsonString(updatedPriceHistory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        List<ProductPriceHistory> priceHistoryList = productService.getProductPriceHistoryByProductId(productId);
        assertThat(priceHistoryList, hasSize(2));
        assertEquals("85.00", priceHistoryList.get(1).getPrice().toString());
    }
}