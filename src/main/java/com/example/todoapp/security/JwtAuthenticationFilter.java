package com.example.todoapp.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.todoapp.service.CurrentUserData;
import com.example.todoapp.service.CurrentUserService;

import com.example.todoapp.model.logging.InfoLoggingContext;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        Authentication authentication = jwtService.verifyAuthHeader(authorizationHeader);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info(InfoLoggingContext.getContext("Authentication Successful").toString());
        }
        filterChain.doFilter(request, response);
    }
}
