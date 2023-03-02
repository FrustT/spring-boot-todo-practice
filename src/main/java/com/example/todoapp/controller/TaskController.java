package com.example.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todoapp.entity.Task;
import com.example.todoapp.service.ITaskService;

import lombok.AllArgsConstructor;

import java.util.List;
import com.example.todoapp.model.responses.TaskResponse;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

        private ITaskService taskService;

        @GetMapping
        public ResponseEntity<List<TaskResponse>> getTasks() {
                return ResponseEntity.ok(
                                TaskResponse.from(
                                                taskService.listTasks()));
        }

        @GetMapping("{id}")
        public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
                return ResponseEntity.ok(
                                new TaskResponse(
                                                taskService.getTask(id)));
        }

        @PostMapping
        public ResponseEntity<TaskResponse> addTask(@RequestBody Task task) {
                return ResponseEntity.status(201).body(
                                new TaskResponse(
                                                taskService.addTask(task)));
        }

        @PutMapping("{id}") // TODO : should update route sends back entity model? or just a message?
        public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
                return ResponseEntity.ok(
                                new TaskResponse(
                                                taskService.updateTask(id, task)));
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
                taskService.deleteTask(id);
                return ResponseEntity.ok("Task deleted successfully");
        }

}
