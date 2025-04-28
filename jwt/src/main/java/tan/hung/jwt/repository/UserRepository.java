package tan.hung.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tan.hung.jwt.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByEmail(String email);
}
