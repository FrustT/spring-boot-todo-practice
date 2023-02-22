package com.example.todoapp.task;

import org.springframework.http.ResponseEntity;

public interface ITaskService {

    ResponseEntity<?> listTasks();

    ResponseEntity<?> getTask(Long id);

    ResponseEntity<?> addTask(Task task);

    ResponseEntity<?> updateTask(Long id, Task task);

    ResponseEntity<?> deleteTask(Long id);

    ResponseEntity<?> assignUserToTask(Long taskId, Long userId);

}
