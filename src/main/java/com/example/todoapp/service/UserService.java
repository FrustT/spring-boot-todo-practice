package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final TaskService taskService;

    @Override
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getUser(Long id) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromDatabase.get();
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> getTasksOfUser(Long id) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromDatabase.get();

        return ResponseEntity.ok(user.getTasks());
    }

    @Override
    public ResponseEntity<?> getTaskOfUser(Long userId, Long taskId) {
        Optional<?> responseFromDatabase = userRepository.findById(userId); // This assignment saves us from calling the
                                                                            // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromDatabase.get();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(404).body("Task not found");
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(409).body("User already exists");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User has been added!");
    }

    @Override
    public void addTaskToUser(Long userId, Task task) { // taskservice checks if user exists (but
                                                                     // validation could also added here)
        taskService.addTask(task);
        taskService.assignUserToTask(task.getId(), userId); // TODO, tightly coupled method, changed return type, fix later
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User oldUser = (User) responseFromDatabase.get();

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
        return ResponseEntity.ok("User has been updated");
    }

    @Override
    public ResponseEntity<?> updateTaskOfUser(Long userId, Long taskId, Task task) {
        Optional<?> responseFromDatabase = userRepository.findById(userId); // This assignment saves us from calling the
                                                                            // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromDatabase.get();

        boolean taskFound = false;
        for (Task t : user.getTasks()) {
            if (t.getId().equals(taskId)) {
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            return ResponseEntity.status(404).body("User does not have this task");
        }

        taskService.updateTask(taskId, task);
        return ResponseEntity.ok("Task updated of user");
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        if (!userExistsById(id)) {
            return ResponseEntity.status(404).body("User not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Users Deleted.");
    }

    @Override
    public ResponseEntity<?> deleteTasksOfUser(Long id) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromDatabase.get();
        user.getTasks().clear();
        userRepository.save(user);
        return ResponseEntity.ok("Tasks of user deleted");
    }

    @Override
    public ResponseEntity<?> deleteTaskOfUser(Long userId, Long taskId) {
        Optional<?> responseFromDatabase = userRepository.findById(userId); // This assignment saves us from calling the
                                                                            // database twice
        if (responseFromDatabase.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        User user = (User) responseFromDatabase.get();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                tasks.remove(task);
                userRepository.save(user);
                return ResponseEntity.ok("Task deleted of user");
            }
        }
        return ResponseEntity.status(404).body("Task not found");
    }

    public boolean userExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
