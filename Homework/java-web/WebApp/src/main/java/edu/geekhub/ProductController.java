package edu.geekhub;

import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import edu.geekhub.product.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ImageRepository imageRepository;
    @GetMapping()

    public String index(Model model) {
        LocalDateTime now = LocalDateTime.now();

        String formattedDateTime = DateTimeFormatter.ISO_DATE_TIME.format(now);

        model.addAttribute("dateTime", formattedDateTime);
        model.addAttribute(
                "products",
                List.copyOf(
                    productService.getProducts()
                )
        );

        return "redirect:/products/index.html";
    }

    @GetMapping("/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product",new Product());
        return "products/new";
    }

    @PostMapping
    public String create(@ModelAttribute("product") Product product,@RequestParam("file") MultipartFile file)
    {
        if(!file.isEmpty())
            product = productService.addImage(product,file);
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("product",productService.getProductById(id));
        return "products/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product,@PathVariable("id") int id){
        productService.update(id, product);
        return "redirect:/products";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
