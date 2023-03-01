package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.TaskService;
import com.example.todoapp.exception.BusinessException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final TaskService taskService;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id); // This assignment saves us from calling the
                                            // database twice
    }

    @Override
    public List<Task> getTasksOfUser(Long id) {
        Optional<User> responseFromDatabase = userRepository.findById(id);
        if (responseFromDatabase.isEmpty())
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
        return responseFromDatabase.get().getTasks();
    }

    @Override
    public ResponseEntity<?> getTaskOfUser(Long userId, Long taskId) { // TODO: YAGNI?
        Optional<?> responseFromDatabase = userRepository.findById(userId); // This assignment saves us from calling the
                                                                            // database twice
        if (responseFromDatabase.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
        }
        User user = (User) responseFromDatabase.get();
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return ResponseEntity.ok(task);
            }
        }
        throw new BusinessException(HttpStatus.NOT_FOUND, "Task not found");
    }

    @Override // TODO later, we need error object to pass
    public ResponseEntity<?> addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new BusinessException(HttpStatus.CONFLICT, "User already exists");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User has been added!");
    }

    @Override // TODO: YAGNI?
    public void addTaskToUser(Long userId, Task task) { // taskservice checks if user exists (but
                                                        // validation could also added here)
        taskService.addTask(task);
        taskService.assignUserToTask(task.getId(), userId); // TODO, tightly coupled method, changed return type, fix
                                                            // later
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
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
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
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
            throw new BusinessException(HttpStatus.NOT_FOUND, "User does not have this task");
        }

        taskService.updateTask(taskId, task);
        return ResponseEntity.ok("Task updated of user");
    }

    @Override
    public boolean deleteUser(Long id) { // returns false if user not found
        if (!userExistsById(id))
            return false;
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public ResponseEntity<?> deleteTasksOfUser(Long id) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
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
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
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
        throw new BusinessException(HttpStatus.NOT_FOUND, "Task not found");
    }

    public boolean userExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public User getUserById(Long id) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
        }

        return (User) responseFromDatabase.get();
    }
}
