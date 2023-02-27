package com.example.todoapp.model.responses;

import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private String role;
    private List<TaskResponse> tasks;
}
