package com.example.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todoapp.service.ITaskService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;

import com.example.todoapp.model.requests.task.TaskCreateRequest;
import com.example.todoapp.model.requests.task.TaskUpdateRequest;
import com.example.todoapp.model.responses.TaskResponse;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

        private ITaskService taskService;

        @GetMapping
        public ResponseEntity<List<TaskResponse>> getTasks() {
                return ResponseEntity.ok(
                                TaskResponse.from(taskService.listTasks()));
        }

        @GetMapping("{id}")
        public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
                return ResponseEntity.ok(
                                new TaskResponse(taskService.getTask(id)));
        }

        @PostMapping
        public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {

                return ResponseEntity.status(201).body(
                                new TaskResponse(taskService.addTask(TaskCreateRequest.from(taskCreateRequest))));
        }

        @PutMapping("{id}")
        public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Long id,
                        @Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
                return ResponseEntity.ok(
                                new TaskResponse(
                                                taskService.updateTask(id, TaskUpdateRequest.from(taskUpdateRequest))));
        }

        @DeleteMapping("{id}")
        public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
                taskService.deleteTask(id);
                return ResponseEntity.ok("Task deleted successfully");
        }

}
