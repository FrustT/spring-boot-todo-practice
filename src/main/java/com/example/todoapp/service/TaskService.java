package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.example.todoapp.exception.BusinessException;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.TaskRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final IUserService userService;

    @Override
    public Task addTask(Task task) { // TODO, instead of Task in parameter, use DTO for validation
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        getTaskById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTask(Long id) {
        return getTaskById(id);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task oldTask = getTaskById(id);
        oldTask.setTitle(task.getTitle()); // TODO : what if user sends null? it will override existing data
        oldTask.setDescription(task.getDescription());
        oldTask.setCompleted(task.isCompleted());
        oldTask.setDueDate(task.getDueDate());
        oldTask.setCompletedDate(task.getCompletedDate());

        return taskRepository.save(oldTask);
    }

    @Override
    public Task assignUserToTask(Long taskId, Long userId) {
        Task task = getTaskById(taskId);
        User user = userService.getUser(userId);

        task.setOwner(user);
        taskRepository.save(task);
        return task;
    }

    public Task getTaskById(Long id) {
        Optional<Task> responseFromDatabase = taskRepository.findById(id);
        if (responseFromDatabase.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return responseFromDatabase.get();
    }

}
