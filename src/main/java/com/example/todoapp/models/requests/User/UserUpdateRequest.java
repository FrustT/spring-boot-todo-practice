package com.example.todoapp.models.requests.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserUpdateRequest {

    private String name;
    private String email;
    private String password;
    private String role;

}
