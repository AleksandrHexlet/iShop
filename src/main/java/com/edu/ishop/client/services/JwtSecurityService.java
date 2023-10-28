package com.edu.ishop.client.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@Service
public class JwtSecurityService {
    @Value("${iShop.jwtSecret}")
    private String jwtSecret;
    @Value("${iShop.jwtRefreshSecret}")
    private String jwtRefreshSecret;
    @Value("${iShop.jwtSecretExpiration}")
    private long jwtSecretExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims, UserDetails userDetails
    ) {
        System.out.println(jwtSecret);
        return Jwts.builder()
                .setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtSecretExpiration))
                .signWith(getSigningKey(jwtSecret), SignatureAlgorithm.HS256).compact();
    }
    public String generateRefreshToken() {
        System.out.println(jwtRefreshSecret);
        Random randomChar = new Random();
        char charRandom = (char)(randomChar.nextInt(26) + 'a');
        return Jwts.builder()
                .signWith(getSigningKey(jwtRefreshSecret + charRandom), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder().setSigningKey(getSigningKey(jwtSecret))
                .build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey(String jwtSecret) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
