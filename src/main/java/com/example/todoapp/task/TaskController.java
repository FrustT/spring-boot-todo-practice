package com.example.todoapp.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String getTasks() {
        return taskService.listTasks();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTask")
    public String addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public String updateTask(@PathVariable("id") String id) {
        return taskService.updateTask(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String deleteTask(@PathVariable("id") String id) {
        return taskService.deleteTask(id);
    }

}
