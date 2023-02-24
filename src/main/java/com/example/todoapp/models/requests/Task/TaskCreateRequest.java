package com.example.todoapp.models.requests.Task;

import lombok.Builder;

import java.time.LocalDate;
import com.example.todoapp.user.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TaskCreateRequest {

    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    private LocalDate completedDate;
    private User owner;
}
