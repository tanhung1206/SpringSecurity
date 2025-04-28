package tan.hung.jwt2.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
public class RegisterDto {
    String firstName;
    String lastName;
    private String email;
    String password;
    String userRole;
}
