package com.example.todoapp.service;

import com.example.todoapp.entity.Task;

import java.util.List;

public interface ITaskService {

    List<Task> listTasks();

    Task getTask(Long id);

    Task addTask(Task task);

    Task updateTask(Long id, Task task);

    void deleteTask(Long id);

    Task assignUserToTask(Long taskId, Long userId);

}
