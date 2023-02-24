package com.example.todoapp.models.requests.Task;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDate;

@Data
@ToString
public class TaskUpdateRequest {

    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
    private LocalDate completedDate;
    private String ownerName;
}
