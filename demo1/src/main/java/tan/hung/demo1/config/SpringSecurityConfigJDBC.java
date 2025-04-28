package tan.hung.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import tan.hung.demo1.filter.TenantFilter2;


import javax.sql.DataSource;

//@Configuration
public class SpringSecurityConfigJDBC {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
//                .addFilterAfter(new TenantFilter(), AuthorizationFilter.class)
                .addFilterBefore(new TenantFilter2(), AuthorizationFilter.class)
                .build();
    }


    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {

        UserDetails admin = User.builder()
                .username("admin2")
                .password(passwordEncoder().encode("12345"))
                .roles("admin")
                .build();
        UserDetails user = User.builder()
                .username("user2")
                .password(passwordEncoder().encode("12345"))
                .roles("user")
                .build();
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(admin);
        return userDetailsManager;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
