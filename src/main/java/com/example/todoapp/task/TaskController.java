package com.example.todoapp.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private ITaskService taskService;

    @GetMapping
    public ResponseEntity<?> getTasks() {
        return taskService.listTasks();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
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

    @PutMapping("{taskId}/assign/{userId}")
    public ResponseEntity<?> assignUserToTask(@PathVariable("taskId") Long taskId,
            @PathVariable("userId") Long userId) {
        return taskService.assignUserToTask(taskId, userId);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        return taskService.deleteTask(id);
    }

}
