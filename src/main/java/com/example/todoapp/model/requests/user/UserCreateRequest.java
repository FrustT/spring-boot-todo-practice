package com.example.todoapp.model.requests.user;

import com.example.todoapp.entity.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserCreateRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotEmpty
    private String role;

    public static User from(@Valid UserCreateRequest userCreateRequest) {
        return User.builder()
                .name(userCreateRequest.getName())
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                // .role(userCreateRequest.getRole())
                .build();
    }
}
