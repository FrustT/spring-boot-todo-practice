package com.example.todoapp.task;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ITaskService {

    List<Task> listTasks();

    Task getTask(Long id);

    ResponseEntity<?> addTask(Task task);

    ResponseEntity<?> updateTask(Long id, Task task);

    ResponseEntity<?> deleteTask(Long id);

    ResponseEntity<?> deleteListOfTasks(List<Task> tasks);

}
