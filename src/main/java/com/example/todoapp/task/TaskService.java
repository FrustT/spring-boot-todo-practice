package com.example.todoapp.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ResponseEntity<?> addTask(Task task) {
        taskRepository.save(task);
        return ResponseEntity.ok().build();
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public ResponseEntity<?> deleteTask(Long id) {
        taskRepository.delete(getTask(id));
        return ResponseEntity.ok().build();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<?> updateTask(Long id, Task task) {
        Task oldTask = getTask(id);
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setCompleted(task.isCompleted());
        oldTask.setDueDate(task.getDueDate());
        oldTask.setCompletedDate(task.getCompletedDate());
        taskRepository.save(oldTask);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deleteListOfTasks(List<Task> tasks) {
        taskRepository.deleteAll(tasks);
        return ResponseEntity.ok().build();
    }

}
