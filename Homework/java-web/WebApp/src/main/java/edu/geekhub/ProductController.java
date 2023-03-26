package edu.geekhub;

import com.google.gson.Gson;
import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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


    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public boolean create(@ModelAttribute Product product, @RequestPart("file") MultipartFile file) {
        if (!file.isEmpty()) {
            product = productService.addImage(product, file);
        }
        productService.addProduct(product);
        return true;
    }



    //@PutMapping("/{id}")
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public boolean update(@RequestBody Product product,@RequestPart("file") MultipartFile file){
        productService.update(product.getId(), product);
        return true;
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
