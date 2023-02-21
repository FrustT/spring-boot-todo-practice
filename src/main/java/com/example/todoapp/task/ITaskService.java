package com.example.todoapp.task;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ITaskService {// TODO: change return types

    List<Task> listTasks();

    Task getTask(String id);

    ResponseEntity<?> addTask(Task task);

    ResponseEntity<?> updateTask(String id, Task task);

    ResponseEntity<?> deleteTask(String id);

}
