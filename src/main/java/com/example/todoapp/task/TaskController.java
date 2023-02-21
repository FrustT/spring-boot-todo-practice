package com.example.todoapp.task;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @GetMapping
    public String getTasks(){
        return taskService.listUsers();
    }

    @PostMapping
    public String postTask(){
        return "posted";
    }

    @PutMapping
    public String putTask(){
        return "updated";
    }

    @DeleteMapping
    public String deleteTask(){
        return "deleted";
    }

}
