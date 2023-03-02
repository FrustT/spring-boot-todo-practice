package com.example.todoapp.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.todoapp.model.requests.auth.AuthenticationRequest;
import com.example.todoapp.model.requests.auth.RegisterRequest;
import com.example.todoapp.model.responses.AuthenticationResponse;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.security.JwtService;

import jakarta.validation.Valid;

import com.example.todoapp.entity.User;
import com.example.todoapp.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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
        if (foundUser != null)
            throw new BusinessException(HttpStatus.CONFLICT, "User already exists");
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
        throw new BusinessException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid username or password");
    }
}
