package com.example.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todoapp.entity.Task;
import com.example.todoapp.service.ITaskService;

import lombok.AllArgsConstructor;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private ITaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        Optional<Task> task = taskService.getTask(id);
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task.get());
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.status(201).body(taskService.addTask(task));
    }

    @PutMapping("{id}") // TODO : should update route sends back entity model? or just a message?
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        Task foundTask = taskService.updateTask(id, task);
        if(foundTask==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(foundTask);
    }

    @PutMapping("{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignUserToTask(@PathVariable("taskId") Long taskId,
            @PathVariable("userId") Long userId) {
        Task task = taskService.assignUserToTask(taskId, userId);
        if (task==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        boolean isFound = taskService.deleteTask(id);
        if (!isFound) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Task deleted successfully");
    }

}
