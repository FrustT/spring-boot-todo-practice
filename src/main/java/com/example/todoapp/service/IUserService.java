package com.example.todoapp.service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUsers();

    Optional<User> getUser(Long id);

    List<Task> getTasksOfUser(Long id);

    ResponseEntity<?> getTaskOfUser(Long userId, Long taskId);

    void addTaskToUser(Long userId, Task task);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(Long id, User user);

    ResponseEntity<?> updateTaskOfUser(Long userId, Long taskId, Task task);

    boolean deleteUser(Long id);

    ResponseEntity<?> deleteTasksOfUser(Long id);

    ResponseEntity<?> deleteTaskOfUser(Long userId, Long taskId);

    boolean userExistsById(Long id);

    User getUserById(Long id);

}
