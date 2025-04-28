package tan.hung.jwt.service;

import tan.hung.jwt.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    void deleteProduct(long id);
}
