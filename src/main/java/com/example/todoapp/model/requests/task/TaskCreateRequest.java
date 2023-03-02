package com.example.todoapp.model.requests.task;

import lombok.Builder;

import java.time.LocalDate;
import com.example.todoapp.entity.User;
import com.example.todoapp.entity.Task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskCreateRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
    @NotEmpty(message = "Due date is required")
    private LocalDate dueDate;
    private LocalDate completedDate;
    private User owner;

    public static Task from(@Valid TaskCreateRequest taskCreateRequest) {
        return Task.builder()
                .title(taskCreateRequest.getTitle())
                .description(taskCreateRequest.getDescription())
                .dueDate(taskCreateRequest.getDueDate())
                .completedDate(taskCreateRequest.getCompletedDate())
                .owner(taskCreateRequest.getOwner())
                .build();
    }
}
