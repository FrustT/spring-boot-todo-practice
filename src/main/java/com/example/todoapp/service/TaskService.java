package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import com.example.todoapp.exception.BusinessException;
import com.example.todoapp.model.logging.WarnLoggingContext;
import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.TaskRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {
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
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setCompleted(task.isCompleted());
        oldTask.setDueDate(task.getDueDate());
        oldTask.setCompletedDate(task.getCompletedDate());

        return taskRepository.save(oldTask);
    }

    @Override
    public Task assignUserToTask(Long taskId, User user) {
        Task task = getTaskById(taskId);

        task.setOwner(user);
        taskRepository.save(task);
        return task;
    }

    public Task getTaskById(Long id) {
        Optional<Task> responseFromDatabase = taskRepository.findById(id);
        if (responseFromDatabase.isEmpty()) {
            log.warn(WarnLoggingContext.getContext("Task not found").toString());
            throw new BusinessException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return responseFromDatabase.get();
    }

}
