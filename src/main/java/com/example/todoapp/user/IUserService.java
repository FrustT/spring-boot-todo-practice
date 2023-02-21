package com.example.todoapp.user;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IUserService {

    List<User> getUsers();

    User getUser(Long id);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(Long id, User user);

    ResponseEntity<?> deleteUser(Long id);

}
