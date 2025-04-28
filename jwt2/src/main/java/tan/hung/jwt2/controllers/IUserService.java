package tan.hung.jwt2.controllers;

import org.springframework.http.ResponseEntity;
import tan.hung.jwt2.dto.LoginDto;
import tan.hung.jwt2.dto.RegisterDto;
import tan.hung.jwt2.models.Role;
import tan.hung.jwt2.models.User;

public interface IUserService {
    String authenticate(LoginDto loginDto);
    ResponseEntity<?> register(RegisterDto registerDto);
    Role saveRole(Role role);
    User saverUser(User user);
}
