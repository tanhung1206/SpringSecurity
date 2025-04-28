package tan.hung.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MyRole {
    @Id
    private int id;
    private String roleName;
}
