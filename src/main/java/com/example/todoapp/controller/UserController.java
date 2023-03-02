package com.example.todoapp.controller;

import com.example.todoapp.entity.Task;
import com.example.todoapp.service.IUserService;
import com.example.todoapp.entity.User;
import com.example.todoapp.model.responses.TaskResponse;
import com.example.todoapp.model.responses.UserResponse;
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

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(UserResponse.from(userService.getUsers()));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("{id}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasksOfUser(@PathVariable("id") Long id) {
        List<Task> tasks = userService.getTasksOfUser(id);
        if (tasks == null) // is this necessary?
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(TaskResponse.from(tasks));
    }

    @GetMapping("/{userId}/task/{taskId}")
    public ResponseEntity<TaskResponse> getTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId) {
        return ResponseEntity.ok(new TaskResponse(userService.getTaskOfUser(userId, taskId)));
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    @PostMapping("/{userId}/task")
    public ResponseEntity<String> addTaskToUser(@PathVariable("userId") Long userId, @RequestBody Task task) {
        userService.addTaskToUser(userId, task); // TODO tightly coupled method, fix later
        return ResponseEntity.ok("Task added successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok("User updated successfully");
    }

    @PutMapping("/{userId}/task/{taskId}")
    public ResponseEntity<String> updateTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId, @RequestBody Task task) {
        userService.updateTaskOfUser(userId, taskId, task);
        return ResponseEntity.ok("Task updated successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @DeleteMapping("/{userId}/task/{taskId}") // TODO: YAGNI?
    public ResponseEntity<String> deleteTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId) {
        userService.deleteTaskOfUser(userId, taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @DeleteMapping("/{id}/tasks")
    public ResponseEntity<String> deleteTasksOfUser(@PathVariable("id") Long id) {
        userService.deleteTasksOfUser(id);
        return ResponseEntity.ok("Tasks deleted successfully");
    }

}
