package com.pesto.task_management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

/**
 * JwtProvider is a service class that handles JWT (JSON Web Token) related operations.
 * It includes methods for generating a token, validating a token, and extracting username from a token.
 */
@Service
public class JwtProvider {

    private Key key;

    /**
     * This method is called after the bean has been constructed.
     * It initializes the key used for signing JWT.
     */
    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    /**
     * This method generates a JWT for the authenticated user.
     * It receives an Authentication object which contains user details.
     * It uses the username from the Authentication object as the subject of the JWT.
     *
     * @param authentication - Authentication object containing user details.
     * @return a String representing the JWT.
     */
    public String generateToken(Authentication authentication) {
        User principle = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principle.getUsername())
                .signWith(key)
                .compact();
    }

    /**
     * This method validates a JWT.
     * It receives a JWT as a string and uses Jwts parser to parse it.
     * If the JWT is valid, it returns true. If the JWT is invalid, it throws an exception.
     * @param jwt - a String representing the JWT.
     * @return a boolean indicating whether the JWT is valid.
     */
    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    /**
     * This method extracts the username from a JWT.
     * It receives a JWT as a string and uses Jwts parser to parse it and get the claims.
     * It then returns the subject of the claims, which is the username.
     * @param token - a String representing the JWT.
     * @return a String representing the username.
     */
    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
