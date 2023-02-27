package com.example.todoapp.service;

import org.springframework.http.ResponseEntity;

import com.example.todoapp.entity.Task;

public interface ITaskService {

    ResponseEntity<?> listTasks();

    ResponseEntity<?> getTask(Long id);

    ResponseEntity<?> addTask(Task task);

    ResponseEntity<?> updateTask(Long id, Task task);

    ResponseEntity<?> deleteTask(Long id);

    ResponseEntity<?> assignUserToTask(Long taskId, Long userId);

}
