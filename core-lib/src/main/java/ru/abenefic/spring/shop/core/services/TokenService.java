package ru.abenefic.spring.shop.core.services;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import ru.abenefic.spring.shop.core.interfaces.ITokenService;
import ru.abenefic.spring.shop.core.model.UserInfo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
public class TokenService implements ITokenService {

    private static final String JWT_SECRET = "jnafhq38rq3iowfnqegui4rqevoyudfhvef4qerjn3iueh93uqrbeg9qawsx54hibpv8y4uihb94uerhbgt389q9";

    @Override
    public String generateToken(UserInfo user) {
        Instant expirationTime = Instant.now().plus(1, ChronoUnit.HOURS);
        Date expirationDate = Date.from(expirationTime);

        String compactTokenString = Jwts.builder()
                .claim("id", user.getId())
                .claim("role", user.getRoles())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();

        return "Bearer " + compactTokenString;
    }

    @Override
    public UserInfo parseToken(String token) throws ExpiredJwtException {
        Jws<Claims> jwsClaims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        Long userId = jwsClaims.getBody()
                .get("id", Long.class);

        List<String> role = jwsClaims.getBody()
                .get("role", List.class);

        return UserInfo.builder()
                .id(userId)
                .roles(role)
                .build();
    }
}
