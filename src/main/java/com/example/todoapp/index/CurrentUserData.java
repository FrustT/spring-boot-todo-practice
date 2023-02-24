package com.example.todoapp.index;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;
import lombok.ToString;

import com.example.todoapp.user.User;

@Data
@ToString
public class CurrentUserData {

    private User user;

    public CurrentUserData() {
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

}
