package com.example.todoapp.model.responses;

import java.time.LocalDate;
import java.util.Collection;

import com.example.todoapp.entity.Task;
import java.util.ArrayList;
import java.util.List;

import lombok.ToString;
import lombok.Data;

@Data
@ToString
public class TaskResponse {// We can limit the data that we want to send to the client by creating a
                           // response class

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
    private LocalDate completedDate;
    private String ownerName;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getCompletedDate() == null ? "Pending" : "Completed";
        this.dueDate = task.getDueDate();
        this.completedDate = task.getCompletedDate();
        this.ownerName = task.getOwner().getName();
    }

    public static List<TaskResponse> from(Collection<Task> tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            taskResponses.add(new TaskResponse(task));
        }
        return taskResponses;
    }
}
