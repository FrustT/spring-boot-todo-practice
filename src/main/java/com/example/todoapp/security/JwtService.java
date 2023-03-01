package com.example.todoapp.security;

import com.example.todoapp.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.security.Key;
import java.util.*;

@Service
@Slf4j
public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5275";
    private static final String TOKEN_ISSUER = "TodoApp";
    private static final int EXPIRE_HOURS = 10; // token expires in 10 hours


    public Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    // TODO: what if dont specify algorithm? vulnerability by giving json with no alg
    public String generateToken(User user){
        return Jwts.builder()
            .signWith(getSigningKey())
            .setIssuer(TOKEN_ISSUER)
            .setSubject(user.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*EXPIRE_HOURS))
            .claim("role", user.getRole()) // extra claim
            .compact();
    }


    /**
     * Takes token from Bearer header and returns Authentication object.
     * @param authHeader whole header like "Bearer eyJhbGciOiJ.."
     * @return Authentication object or null if failed
     */
    public Authentication verifyAuthHeader(String authHeader){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        String token = authHeader.substring(7);

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);

            String subject = jws.getBody().getSubject();
            String role = jws.getBody().get("role", String.class);
            Collection<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));
            Authentication authentication = new UsernamePasswordAuthenticationToken(subject,role,authorities);
            return authentication;

        } catch (Exception e) { // TODO : specify later
            log.error(e.getMessage());
        }

        return null; // TODO later
    }
}