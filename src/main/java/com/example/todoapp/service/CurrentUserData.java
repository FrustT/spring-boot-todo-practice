package com.example.todoapp.service;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;
import lombok.ToString;

import com.example.todoapp.entity.User;

@Data
@ToString
public class CurrentUserData {

    private String username;

    public CurrentUserData() {
        this.username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

}
