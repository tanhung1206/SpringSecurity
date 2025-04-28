package tan.hung.jwt2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tan.hung.jwt2.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);
    Optional<User>findByEmail(String email);
}
