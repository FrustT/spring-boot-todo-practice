package com.example.todoapp.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.task.ITaskService;
import com.example.todoapp.task.Task;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

}
