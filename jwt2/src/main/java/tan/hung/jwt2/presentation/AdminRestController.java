package tan.hung.jwt2.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tan.hung.jwt2.models.User;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
    @GetMapping("/hello")
    public String hello(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        User user= (User) authentication.getPrincipal();
//        System.out.println(user);
        System.out.println(authentication);
        return "Welcome you are authenticated as Admin!";
    }
}
