package com.example.todoapp.model.requests.user;

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
