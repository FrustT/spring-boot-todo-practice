package com.example.todoapp.model.requests.task;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

import com.example.todoapp.entity.User;
import com.example.todoapp.entity.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

@Data
@ToString
public class TaskUpdateRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
    private boolean completed;
    @NotEmpty(message = "Due date is required")
    private LocalDate dueDate;
    private LocalDate completedDate;
    private User owner;

    public static Task from(@Valid TaskUpdateRequest taskUpdateRequest) {
        return Task.builder()
                .title(taskUpdateRequest.getTitle())
                .description(taskUpdateRequest.getDescription())
                .dueDate(taskUpdateRequest.getDueDate())
                .completedDate(taskUpdateRequest.getCompletedDate())
                .owner(taskUpdateRequest.getOwner())
                .build();
    }
}
