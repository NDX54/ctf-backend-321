package com.csit321.ctfbackend.core.config;

import com.csit321.ctfbackend.core.api.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String SECRET_KEY = "d35c6bc108fd7feaf8e2b04b55abe080cb7d8abd59417aa2760fcd76caeedd2a";

    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            throw new JwtException("Failed to extract username from token: " + e.getMessage());
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return generateToken(claims, userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        try {
            return Jwts
                    .builder()
                    .subject(userDetails.getUsername())
                    .claim("role", userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .findFirst().orElse(null))
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 180 * 24))
                    .signWith(getSignInKey())
                    .compact();
        } catch (Exception e) {
            throw new JwtException("Failed to generate token: " + e.getMessage());
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {

        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (Exception e) {
            throw new JwtException("Token validation failed: " + e.getMessage());
        }

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT signature: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            throw new JwtException("Expired JWT token: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Unsupported JWT token: " + e.getMessage());
        } catch (MalformedJwtException e) {
            throw new JwtException("Malformed JWT token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JwtException("Illegal JWT token: " + e.getMessage());
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
