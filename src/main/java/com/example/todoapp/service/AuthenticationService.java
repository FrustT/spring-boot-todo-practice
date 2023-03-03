package com.example.todoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.todoapp.model.logging.WarnLoggingContext;
import com.example.todoapp.model.requests.auth.AuthenticationRequest;
import com.example.todoapp.model.requests.auth.RegisterRequest;
import com.example.todoapp.model.responses.AuthenticationResponse;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.security.JwtService;

import com.example.todoapp.entity.User;
import com.example.todoapp.exception.BusinessException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        // check if user exists before register
        User foundUser = userRepository.findByEmail(request.getEmail());
        if (foundUser != null) {
            log.warn(WarnLoggingContext.getContext("User already exists").toString());
            throw new BusinessException(HttpStatus.CONFLICT, "User already exists");
        }
        User response = userRepository.save(user);
        var jwtToken = jwtService.generateToken(response);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByEmail(request.getUsername());
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        }
        log.warn(WarnLoggingContext.getContext("Invalid Username or password").toString());
        throw new BusinessException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid username or password");
    }
}
