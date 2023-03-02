package com.example.todoapp.model.responses;

import lombok.Data;
import lombok.ToString;
import java.util.List;
import com.example.todoapp.entity.User;
import com.example.todoapp.entity.Role;
import java.util.ArrayList;
import java.util.Collection;

@Data
@ToString
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private List<TaskResponse> tasks;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.tasks = TaskResponse.from(user.getTasks());
    }

    public static List<UserResponse> from(Collection<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(new UserResponse(user));
        }
        return userResponses;
    }
}
