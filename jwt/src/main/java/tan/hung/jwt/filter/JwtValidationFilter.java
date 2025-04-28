package tan.hung.jwt.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tan.hung.jwt.model.User;
import tan.hung.jwt.util.JwtTokenUtil;

import java.io.IOException;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    public JwtValidationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenUtil.validateToken(token)) {
                Claims claims = jwtTokenUtil.getClaimsFromToken(token);
                String subject =claims.getSubject();
                String[] subjects = subject.split(",");
                User user = new User();
                user.setId(Integer.parseInt(subjects[0]));
                user.setEmail(subjects[1]);
                user.setRole(subjects[2]);
                SecurityContext context= SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                context.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(context);
                filterChain.doFilter(request,response);

            } else {
                filterChain.doFilter(request, response);
                return;
            }
        } else {
            filterChain.doFilter(request, response);
            return;
        }
    }
}
