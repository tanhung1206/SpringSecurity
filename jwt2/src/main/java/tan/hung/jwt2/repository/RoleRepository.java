package tan.hung.jwt2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tan.hung.jwt2.models.Role;
import tan.hung.jwt2.models.RoleName;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByRoleName(RoleName roleName);
}
