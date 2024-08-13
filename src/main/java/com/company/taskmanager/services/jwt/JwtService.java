package com.company.taskmanager.services.jwt;

import com.company.taskmanager.exceptions.JwtExpiredException;
import com.company.taskmanager.models.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import java.util.function.Function;

/**
 * Service class for handling JWT (JSON Web Token) operations
 * such as token generation, extraction of claims
 * (e.g., username, expiration date), and validation.
 * <p>
 * This service is responsible for:
 * - Generating JWT tokens for authenticated users.
 * - Extracting specific claims from the token, such as the username
 * or expiration date.
 * - Validating the token to ensure it is still valid and matches the
 * user's details.
 * - Handling the signing and expiration of the token.
 */
@Service
public class JwtService {
    @Value("${TOKEN_KEY}")
    private String jwtSigningKey;

    @Value("${TOKEN_EXPIRATION}")
    private long jwtExpiration;

    /**
     * Extract username from token
     *
     * @param token token
     * @return username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * Extract data from a token
     *
     * @param token token
     * @param claimsResolvers function of data extraction
     * @param <T> data type
     * @return data
     */
    private <T> T extractClaim(String token,
                               Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Token generation
     *
     * @param userDetails user data
     * @return token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("email", customUserDetails.getEmail());
            claims.put("role", customUserDetails.getRole());
        }
        return generateToken(claims, userDetails);
    }

    /**
     * Token generation
     *
     * @param extraClaims extra data
     * @param userDetails user data
     * @return token
     */
    private String generateToken(Map<String, Object>
                                         extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * Token validity check
     *
     * @param token token
     * @param userDetails user data
     * @return true if token is valid
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()))
                && !isTokenExpired(token);
    }

    /**
     * Check token for expiration
     *
     * @param token token
     * @return true if the token is expired
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extract token expiration date
     *
     * @param token token
     * @return expiration date
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract all data from the token
     *
     * @param token token
     * @return data
     */
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("Token lifetime is over: "
                    + e.getMessage());
        }
    }

    /**
     * Obtaining a key for token signing
     *
     * @return key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Getting token validity time
     *
     * @return seconds
     */
    public long getExpirationTime() {
        return jwtExpiration;
    }
}
