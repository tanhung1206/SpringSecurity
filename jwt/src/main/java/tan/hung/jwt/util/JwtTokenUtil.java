package tan.hung.jwt.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tan.hung.jwt.model.User;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static final Logger log= LoggerFactory.getLogger(JwtTokenUtil.class);
    @Value("${app.jwt.secret}")
    private String SECRET;

    @Value("${app.jwt.expiration}")
    private long EXPIRATION;

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId() + "," + user.getEmail() + "," + user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        return false;
    }
    public Claims getClaimsFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }
}
