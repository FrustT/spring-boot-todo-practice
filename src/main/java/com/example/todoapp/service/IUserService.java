package com.example.todoapp.service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getUsers();

    User getUser(Long id);

    List<Task> getTasksOfUser(Long id);

    Task getTaskOfUser(Long userId, Long taskId);

    void addTaskToUser(Long userId, Task task);

    void addUser(User user);

    void updateUser(Long id, User user);

    void updateTaskOfUser(Long userId, Long taskId, Task task);

    boolean deleteUser(Long id);

    void deleteTasksOfUser(Long id);

    void deleteTaskOfUser(Long userId, Long taskId);

    boolean userExistsById(Long id);

    User getUserById(Long id);

}
