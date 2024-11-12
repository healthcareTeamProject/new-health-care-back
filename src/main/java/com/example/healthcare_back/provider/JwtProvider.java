package com.example.healthcare_back.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    @Value("${jwt.secret}")
    private String secretKey;

    // JWT 생성 메서드
    public String create(String userId) {
        Date expiredDate = Date.from(Instant.now().plus(10, ChronoUnit.HOURS));
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    // JWT 검증 메서드 (만료된 토큰 처리)
    public String validate(String jwt) {
        try {
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired: " + e.getMessage());
            return null; // 만료된 경우 null 반환
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}