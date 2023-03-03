package com.example.todoapp.service;

import java.util.List;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.model.requests.task.TaskUpdateRequest;

public interface ICurrentUserService {

    public User getCurrentUser(Long currentUserId);

    public List<Task> getTasksOfCurrentUser(Long currentUserId);

    public Task getTaskOfCurrentUser(Long taskId, Long currentUserId);

    public Task createTaskToCurrentUser(Task task, Long currentUserId);

    public User updateCurrentUser(User user, Long currentUserId);

    public Task updateTaskOfCurrentUser(TaskUpdateRequest taskUpdateRequest, Long taskId, Long currentUserId);

    public void deleteCurrentUser(Long currentUserId);

    public void deleteTaskOfCurrentUser(Long taskId, Long currentUserId);

    public void deleteTasksOfCurrentUser(Long currentUserId);

}
