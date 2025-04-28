package tan.hung.demo1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tan.hung.demo1.model.MyUser;
import tan.hung.demo1.repository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<MyUser>userOptional=userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            MyUser user=userOptional.get();
//            System.out.println(user.getEmail());
//            System.out.println(user.getMyRoleList());
            UserDetails userDetails= User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles()
                    .authorities(user.getMyRoleList().stream().map(r->new SimpleGrantedAuthority(r.getRoleName())).toList())
                    .build();
            return userDetails;
        }
        else{
            throw new UsernameNotFoundException(email);
        }
    }
}
