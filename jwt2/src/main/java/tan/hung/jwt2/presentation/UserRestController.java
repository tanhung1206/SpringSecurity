package tan.hung.jwt2.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tan.hung.jwt2.controllers.IUserService;
import tan.hung.jwt2.dto.LoginDto;
import tan.hung.jwt2.dto.RegisterDto;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserService iUserService;
    @PostMapping("/register")
    public ResponseEntity<?>register(@RequestBody RegisterDto registerDto){
        return iUserService.register(registerDto);
    }
    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto){
        return iUserService.authenticate(loginDto);
    }
}
