package tan.hung.demo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tan.hung.demo1.model.MyUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Integer> {
    Optional<MyUser>findByEmail(String email);
}
