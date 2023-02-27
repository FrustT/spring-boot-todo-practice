package com.example.todoapp.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskService implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> addTask(Task task) {
        taskRepository.save(task);
        return ResponseEntity.ok("Task added successfully");
    }

    @Override
    public ResponseEntity<?> listTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @Override
    public ResponseEntity<?> deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Task not found");
        }
        taskRepository.deleteById(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @Override
    public ResponseEntity<?> getTask(Long id) {
        Optional<?> responseFromDatabase = taskRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        Task task = (Task) responseFromDatabase.get();
        return ResponseEntity.ok(task);
    }

    @Override
    public ResponseEntity<?> updateTask(Long id, Task task) {
        Optional<?> responseFromDatabase = taskRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        Task oldTask = (Task) responseFromDatabase.get();
        oldTask.setTitle(task.getTitle());
        oldTask.setDescription(task.getDescription());
        oldTask.setCompleted(task.isCompleted());
        oldTask.setDueDate(task.getDueDate());
        oldTask.setCompletedDate(task.getCompletedDate());
        taskRepository.save(oldTask);
        return ResponseEntity.ok("Task updated successfully");
    }

    @Override
    public ResponseEntity<?> assignUserToTask(Long taskId, Long userId) {
        Optional<?> responseFromTaskDatabase = taskRepository.findById(taskId); // This assignment saves us from calling
                                                                                // the database twice
        if (responseFromTaskDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("Task not found");
        }
        Task task = (Task) responseFromTaskDatabase.get();

        Optional<?> responseFromUserDatabase = userRepository.findById(userId); // This assignment saves us from calling
                                                                                // the database twice
        if (responseFromUserDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromUserDatabase.get();
        task.setOwner(user);
        taskRepository.save(task);
        return ResponseEntity.ok("User assigned to task successfully");
    }

}
