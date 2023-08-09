package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductPriceHistory;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getById(id);
        if (existingProduct != null) {
            updatedProduct.setId(id);
            productService.update(updatedProduct);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            productService.remove(product);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/price")
    public ResponseEntity<Void> addProductPriceHistory(
            @PathVariable Long id,
            @RequestBody ProductPriceHistory productPriceHistory
    ) {
        Product product = productService.getById(id);
        if (product != null) {
            productService.addProductPriceHistory(productPriceHistory);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<List<ProductPriceHistory>> getProductPriceHistoryByProductId(@PathVariable Long id) {
        List<ProductPriceHistory> priceHistoryList = productService.getProductPriceHistoryByProductId(id);
        if (!priceHistoryList.isEmpty()) {
            return ResponseEntity.ok(priceHistoryList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Void> updateProductPrice(
            @RequestBody ProductPriceHistory productPriceHistory
    ) {
        Product product = productService.getById(productPriceHistory.getProduct().getId());
        if (product != null) {
            productService.updateProductPrice(productPriceHistory);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
