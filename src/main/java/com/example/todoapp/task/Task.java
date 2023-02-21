package com.example.todoapp.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@Table
@AllArgsConstructor
public class Task {

    @Id
    private String id;
    private String title;
    private String description;
    private boolean completed;

}
