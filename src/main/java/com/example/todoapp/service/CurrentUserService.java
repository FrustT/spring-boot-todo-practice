package com.example.todoapp.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.model.requests.task.TaskUpdateRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Lazy
public class CurrentUserService implements ICurrentUserService {

    private IUserService userService;
    private ITaskService taskService;

    @Override
    public User getCurrentUser(Long currentUserId) {
        return userService.getUser(currentUserId);

    }

    @Override
    public List<Task> getTasksOfCurrentUser(Long currentUserId) {
        return userService.getTasksOfUser(currentUserId);
    }

    @Override
    public Task getTaskOfCurrentUser(Long taskId, Long currentUserId) {
        return userService.getTaskOfUser(currentUserId, taskId);
    }

    @Override
    public Task createTaskToCurrentUser(Task task, Long currentUserId) {
        userService.addTaskToUser(currentUserId, task);
        return task;
    }

    @Override
    public User updateCurrentUser(User user, Long currentUserId) {
        userService.updateUser(currentUserId, user);
        return user;
    }

    @Override
    public Task updateTaskOfCurrentUser(TaskUpdateRequest taskUpdateRequest, Long taskId, Long currentUserId) {
        return taskService.updateTask(taskId, TaskUpdateRequest.from(taskUpdateRequest));
    }

    @Override
    public void deleteCurrentUser(Long currentUserId) {
        userService.deleteUser(currentUserId);// NEEDS TO DELETE USERS' JWT TOKENS
    }

    @Override
    public void deleteTaskOfCurrentUser(Long taskId, Long currentUserId) {
        taskService.deleteTask(taskId);
    }

    @Override
    public void deleteTasksOfCurrentUser(Long currentUserId) {
        userService.deleteTasksOfUser(currentUserId);
    }

}
