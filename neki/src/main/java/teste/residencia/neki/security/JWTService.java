package teste.residencia.neki.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import teste.residencia.neki.model.User;

@Component
public class JWTService {
    
    private static final String privateKeyJWT = "secretKey";

    public String gerarToken(Authentication authentication){

        int expiryTime = 86400000;
        Date expiryDate = new Date(new Date().getTime() + expiryTime);

        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, privateKeyJWT)
                .compact();
    }

    public Optional<Long> obterIdUser(String token){

        try {
            Claims claims = parse(token).getBody();

            return Optional.ofNullable(Long.parseLong(claims.getSubject()));

        } catch (Exception e) {
          
            return Optional.empty();
        }

    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser().setSigningKey(privateKeyJWT).parseClaimsJws(token);
    }
}
