package tan.hung.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;
import tan.hung.jwt.model.Product;

@RestController
public interface ProductRepository extends JpaRepository<Product, Long>{

}
