package com.allennagtalon.wishlistwebapp.service;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtils {
  
  @Value("${jwt.encryption.key}")
  private String encryptionKey;

  private static final long EXPIRATION_TIME = 86400000;

  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(getSignInKey())
      .compact();
  }

  public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
    return Jwts.builder()
      .setClaims(claims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .signWith(getSignInKey())
      .compact();
  }

  public String extractUsername(String token) {
    return extractClaims(token, Claims::getSubject);
  }

  private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
    return claimsResolver.apply(
      Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
    );
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractClaims(token, Claims::getExpiration).before(new Date());
  }

  private SecretKey getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(encryptionKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
