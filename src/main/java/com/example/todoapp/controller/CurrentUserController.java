package com.example.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.entity.User;
import com.example.todoapp.model.requests.task.TaskUpdateRequest;
import com.example.todoapp.model.requests.user.UserUpdateRequest;
import com.example.todoapp.model.responses.TaskResponse;
import com.example.todoapp.model.responses.UserResponse;
import com.example.todoapp.service.ICurrentUserService;
import com.example.todoapp.service.CurrentUserData;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/me")
@AllArgsConstructor
public class CurrentUserController {
    private ICurrentUserService currentUserService;

    @GetMapping
    public ResponseEntity<UserResponse> getCurrentUser(CurrentUserData currentUserData) {
        User user = currentUserService.getCurrentUser(currentUserData.getUserId());
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("tasks")
    public ResponseEntity<List<TaskResponse>> getTasksOfCurrentUser(CurrentUserData currentUserData) {
        List<TaskResponse> tasksResponses = TaskResponse
                .from(currentUserService.getTasksOfCurrentUser(currentUserData.getUserId()));
        return ResponseEntity.ok(tasksResponses);
    }

    @GetMapping("tasks/{taskId}")
    public ResponseEntity<TaskResponse> getTaskOfCurrentUser(@PathVariable("taskId") Long taskId,
            CurrentUserData currentUserData) {
        TaskResponse taskResponse = new TaskResponse(
                currentUserService.getTaskOfCurrentUser(taskId, currentUserData.getUserId()));
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateCurrentUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest,
            CurrentUserData currentUserData) {
        return ResponseEntity
                .ok(new UserResponse(currentUserService.updateCurrentUser(UserUpdateRequest.from(userUpdateRequest),
                        currentUserData.getUserId())));
    }

    @PutMapping("tasks/{taskId}")
    public ResponseEntity<TaskResponse> updateTaskOfCurrentUser(@Valid @RequestBody TaskUpdateRequest taskUpdateRequest,
            @PathVariable("taskId") Long taskId, CurrentUserData currentUserData) {
        return ResponseEntity
                .ok(new TaskResponse(currentUserService.updateTaskOfCurrentUser(taskUpdateRequest, taskId,
                        currentUserData.getUserId())));
    }

    @DeleteMapping("tasks/{taskId}")
    public ResponseEntity<TaskResponse> deleteTaskOfCurrentUser(@PathVariable("taskId") Long taskId,
            CurrentUserData currentUserData) {
        return ResponseEntity
                .ok(new TaskResponse(currentUserService.getTaskOfCurrentUser(taskId, currentUserData.getUserId())));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCurrentUser(CurrentUserData currentUserData) {
        currentUserService.deleteCurrentUser(currentUserData.getUserId());
        return ResponseEntity.ok("Current user deleted");
    }

    @DeleteMapping("tasks")
    public ResponseEntity<String> deleteAllTasksOfCurrentUser(CurrentUserData currentUserData) {
        currentUserService.deleteTasksOfCurrentUser(currentUserData.getUserId());
        return ResponseEntity.ok("All tasks of current user deleted");
    }

}
