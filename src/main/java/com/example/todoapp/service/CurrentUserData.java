package com.example.todoapp.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.todoapp.entity.User;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@Getter
public class CurrentUserData {

    private User user;

    public CurrentUserData() {
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String getUsername() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public Long getUserId() {
        return user.getId();
    }

}
