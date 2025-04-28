package tan.hung.jwt2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import tan.hung.jwt2.controllers.IUserService;
import tan.hung.jwt2.models.Role;
import tan.hung.jwt2.models.RoleName;
import tan.hung.jwt2.repository.RoleRepository;
import tan.hung.jwt2.repository.UserRepository;

@SpringBootApplication
public class Jwt2Application {

    public static void main(String[] args) {
        SpringApplication.run(Jwt2Application.class, args);
    }

//    @Bean
//    CommandLineRunner run(IUserService iuserService, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        return (args) -> {
//            iuserService.saveRole(new Role(RoleName.USER));
//            iuserService.saveRole(new Role(RoleName.ADMIN));
//        };
//    }

}
