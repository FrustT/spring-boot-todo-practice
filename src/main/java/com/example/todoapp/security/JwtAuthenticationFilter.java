package com.example.todoapp.security;

import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.todoapp.model.logging.InfoLoggingContext;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public Authentication claimsToAuthentication(Jws<Claims> jws){
        String email = jws.getBody().getSubject(); // email
        User user = userRepository.findByEmail(email);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        Jws<Claims> jws = jwtService.verifyAuthHeader(authorizationHeader);
        Authentication authentication = claimsToAuthentication(jws);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info(InfoLoggingContext.getContext("Authentication Successful").toString());

        filterChain.doFilter(request, response);
    }
}
