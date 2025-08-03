package com.vpos.global.jwt.service;

import com.vpos.global.jwt.dto.response.AuthDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.access-expiration}")
    private long accessTokenExpiration; // ms 단위

    @Value("${security.jwt.refresh-expiration}")
    private long refreshTokenExpiration; // ms 단위

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes()); // base64 아님!
    }

    // AccessToken 토큰 생성

    /**
     *
     * @param id: User key value
     * @param userId: User signup Id
     * @param role
     * @return
     */
    public String createAccessToken(String id, String userId, String role) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenExpiration);

        return Jwts.builder()
                .setSubject(id)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // RefreshToken 생성
    public AuthDto.RefreshTokenWithExpiry createRefreshToken(String id) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenExpiration);

        String token = Jwts.builder()
                .setSubject(id)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return new AuthDto.RefreshTokenWithExpiry(token, expiry);
    }

    // 토큰 검증
    public Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // 서명 유효성 검증 + Base64 디코딩, Claims 추출
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            log.warn("JWT parsing error: {}", e.getMessage(), e);
            throw new JwtException("Invalid token", e);
        }
    }

}