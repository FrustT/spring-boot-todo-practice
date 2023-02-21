package com.example.todoapp.task;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private ITaskService taskService;

    @GetMapping
    public List<Task> getTasks() {
        return taskService.listTasks();
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        return taskService.deleteTask(id);
    }

}
