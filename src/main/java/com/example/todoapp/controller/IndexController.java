package com.example.todoapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.service.CurrentUserData;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {

    @GetMapping
    public String index(CurrentUserData currentUserData) {

        return "Welcome to the Todo App API , " + currentUserData.getUsername() + "!";
    }
}
