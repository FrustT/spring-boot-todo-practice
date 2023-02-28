package com.example.todoapp.service;

import org.springframework.http.ResponseEntity;

import com.example.todoapp.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskService {

    List<Task> listTasks();

    Optional<Task> getTask(Long id);

    Task addTask(Task task);

    Task updateTask(Long id, Task task);

    boolean deleteTask(Long id);

    Task assignUserToTask(Long taskId, Long userId);

}
