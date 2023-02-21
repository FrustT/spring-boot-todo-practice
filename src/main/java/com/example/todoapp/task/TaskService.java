package com.example.todoapp.task;

import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

    @Override
    public String addTask(Task task) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String listTasks() {
        return new Task(1L, "addAnExampleTaskForDebugging", "testing is important", true, null, null).toString();
    }

    @Override
    public String deleteTask(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getTask(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updateTask(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}
