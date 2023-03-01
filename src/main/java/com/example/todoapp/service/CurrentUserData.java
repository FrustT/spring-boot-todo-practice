package com.example.todoapp.service;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CurrentUserData {

    private String username;

    public CurrentUserData() {
        this.username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

}
