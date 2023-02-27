package com.example.todoapp.service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;

import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity<?> getUsers();

    ResponseEntity<?> getUser(Long id);

    ResponseEntity<?> getTasksOfUser(Long id);

    ResponseEntity<?> getTaskOfUser(Long userId, Long taskId);

    ResponseEntity<?> addTaskToUser(Long userId, Task task);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(Long id, User user);

    ResponseEntity<?> updateTaskOfUser(Long userId, Long taskId, Task task);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<?> deleteTasksOfUser(Long id);

    ResponseEntity<?> deleteTaskOfUser(Long userId, Long taskId);

    boolean userExistsById(Long id);

    User getUserById(Long id);

}
