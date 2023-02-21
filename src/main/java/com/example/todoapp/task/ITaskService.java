package com.example.todoapp.task;

public interface ITaskService {// TODO: change return types

    String listTasks();

    String getTask(String id);

    String addTask(Task task);

    String updateTask(String id);

    String deleteTask(String id);

}
