package edu.geekhub.controllers;

import com.google.gson.Gson;
import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping()
    public String getAllProducts() {
        return new Gson().toJson(productService.getProducts());
    }

    @GetMapping("{id}")
    public String productById(@PathVariable("id") Integer id) {
        return new Gson().toJson(productService.getProductById(id));
    }

    @PostMapping
    public boolean saveProduct(@RequestParam(value = "file", required = false) MultipartFile file,
                               @RequestParam("name") String name,
                               @RequestParam("price") int price,
                               @RequestParam("rating") int rating,
                               @RequestParam("quantityOnStock") int quantityOnStock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setRating(rating);
        product.setQuantityOnStock(quantityOnStock);
        if (file != null) {
            product.setImgBytes(file);
        }
        productService.addProduct(product);
        return true;
    }


    @PutMapping("/{id}")
    public boolean updateProduct(@PathVariable int id,
                                 @RequestParam(value = "file", required = false) MultipartFile file,
                                 @RequestParam("name") String name,
                                 @RequestParam("price") int price,
                                 @RequestParam("rating") int rating,
                                 @RequestParam("quantityOnStock") int quantityOnStock) {
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setPrice(price);
        product.setRating(rating);
        product.setQuantityOnStock(quantityOnStock);
        if (file != null) {
            product.setImgBytes(file);
        }
        productService.update(id,product);
        return true;
    }


    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return true;
    }

    @GetMapping(value = "/products/{id}/picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProductPicture(@PathVariable int id) {
        try {
            byte[] imageBytes = productService.getProductById(id).getImgBytes();
            return ResponseEntity.ok().body(imageBytes);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
