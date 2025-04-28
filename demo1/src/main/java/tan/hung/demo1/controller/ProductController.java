package tan.hung.demo1.controller;

import org.springframework.web.bind.annotation.*;
import tan.hung.demo1.model.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping("/")
    public List<Product> getProducts() {
        return List.of(
                new Product(1, "HP", 100),
                new Product(2, "DELL", 99)
        );
    }
    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){
        System.out.println(product);
        return product;
    }
}
