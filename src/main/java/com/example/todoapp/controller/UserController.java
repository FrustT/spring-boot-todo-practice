package com.example.todoapp.controller;

import com.example.todoapp.entity.Task;
import com.example.todoapp.service.IUserService;
import com.example.todoapp.entity.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUser(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get());
    }

    @GetMapping("{id}/tasks")
    public ResponseEntity<?> getTasksOfUser(@PathVariable("id") Long id) {
        List<Task> tasks = userService.getTasksOfUser(id);
        if (tasks==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{userId}/task/{taskId}")
    public ResponseEntity<?> getTaskOfUser(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        return userService.getTaskOfUser(userId, taskId);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/task")
    public void addTaskToUser(@PathVariable("userId") Long userId, @RequestBody Task task) {
        userService.addTaskToUser(userId, task); // TODO tightly coupled method, fix later
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/{userId}/task/{taskId}")
    public ResponseEntity<?> updateTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId, @RequestBody Task task) {
        return userService.updateTaskOfUser(userId, taskId, task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        boolean foundUser = userService.deleteUser(id);
        if (!foundUser) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("/{userId}/task/{taskId}") // TODO: YAGNI?
    public ResponseEntity<?> deleteTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId) {
        return userService.deleteTaskOfUser(userId, taskId);
    }

    @DeleteMapping("/{id}/tasks")
    public ResponseEntity<?> deleteTasksOfUser(@PathVariable("id") Long id) {
        return userService.deleteTasksOfUser(id);
    }

}
