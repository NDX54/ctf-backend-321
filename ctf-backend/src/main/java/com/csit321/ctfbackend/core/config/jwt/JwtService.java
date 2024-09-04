package com.csit321.ctfbackend.core.config.jwt;

import com.csit321.ctfbackend.core.api.exceptions.JwtException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Service for generating and validating JWT tokens.
@Service
public class JwtService {

    // Secret key for signing JWT tokens.
    @Value("${jwt.secret.key}")
    private String secretKey;

    // Method to extract the username from a JWT token.
    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        } catch (Exception e) {
            throw new JwtException("Failed to extract username from token: " + e.getMessage());
        }
    }

    // Method to extract a specific claim from a JWT token.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to generate a JWT token for a given user.
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        return generateToken(claims, userDetails);
    }

    // Method to generate a JWT token with additional claims.
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

    // Method to validate a JWT token.
    public boolean isTokenValid(String token, UserDetails userDetails) {

        try {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        } catch (Exception e) {
            throw new JwtException("Token validation failed: " + e.getMessage());
        }

    }

    // Method to check if a JWT token is expired.
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method to extract the expiration date from a JWT token.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Method to extract all claims from a JWT token.
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

    // Method to get the signing key for JWT tokens.
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
