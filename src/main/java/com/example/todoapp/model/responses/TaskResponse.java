package com.example.todoapp.model.responses;

import java.time.LocalDate;

import lombok.ToString;
import lombok.Data;

@Data
@ToString
public class TaskResponse {

    private String id;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
    private LocalDate completedDate;
    private String ownerName;
}
