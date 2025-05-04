package org.restaurantmanager.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public static final String JWT_SECRET = "aGH654b32pgGh+RuyZnIPEAreWDL+r/Uyq0rODqFKVs=";

    public String generateToken(String email){
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String email){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
