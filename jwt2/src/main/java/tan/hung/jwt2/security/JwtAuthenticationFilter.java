package tan.hung.jwt2.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtilities jwtUtilities;
    private final CustomerUserDetailsService customerUserDetailsService;

    public JwtAuthenticationFilter(CustomerUserDetailsService customerUserDetailsService, JwtUtilities jwtUtilities) {
        this.customerUserDetailsService = customerUserDetailsService;
        this.jwtUtilities = jwtUtilities;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=jwtUtilities.getToken(request);
        if(token!=null&&jwtUtilities.validateToken(token)){
            String email=jwtUtilities.extractUsername(token);
            UserDetails userDetails=customerUserDetailsService.loadUserByUsername(email);
            if(userDetails!=null){
//                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(null,null,userDetails.getAuthorities());
                log.info("authenticated user with email: {}",email);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}