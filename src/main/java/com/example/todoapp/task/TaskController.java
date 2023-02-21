package com.example.todoapp.task;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getTasks() {
        return taskService.listUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTask")
    public String postTask() {
        return "posted";
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public String putTask() {
        return "updated";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String deleteTask() {
        return "deleted";
    }

}
