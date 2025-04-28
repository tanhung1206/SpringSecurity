package tan.hung.demo1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class MyUser {
    @Id
    private int id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private List<MyRole> myRoleList;
}
