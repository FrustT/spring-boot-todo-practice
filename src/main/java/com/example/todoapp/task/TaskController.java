package com.example.todoapp.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Task> getTasks() {
        return taskService.listTasks();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Task getTask(@PathVariable String id) {
        return taskService.getTask(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addtask")
    public ResponseEntity<?> addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<?> updateTask(@PathVariable("id") String id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        return taskService.deleteTask(id);
    }

}
