package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import com.example.todoapp.entity.Task;
import com.example.todoapp.entity.User;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.exception.BusinessException;
import com.example.todoapp.model.logging.WarnLoggingContext;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final TaskService taskService;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return getUserById(id);
    }

    @Override
    public List<Task> getTasksOfUser(Long id) {
        User user = getUserById(id);
        return user.getTasks();
    }

    @Override
    public Task getTaskOfUser(Long userId, Long taskId) { // TODO: YAGNI?

        User user = getUserById(userId);
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        log.warn(WarnLoggingContext.getContext("Task not found").toString());
        throw new BusinessException(HttpStatus.NOT_FOUND, "Task not found");
    }

    @Override
    public void addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            log.warn(WarnLoggingContext.getContext("User already exists").toString());
            throw new BusinessException(HttpStatus.CONFLICT, "User already exists");
        }
        userRepository.save(user);
    }

    @Override
    public void addTaskToUser(Long userId, Task task) {
        User user = getUserById(userId);
        Task newTask = taskService.addTask(task);
        taskService.assignUserToTask(newTask.getId(), user);
    }

    @Override
    public void assignTaskToUser(Long userId, Long taskId) {
        User user = getUserById(userId);
        taskService.assignUserToTask(taskId, user);
    }

    @Override
    public void updateUser(Long id, User user) {

        User oldUser = getUserById(id);

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userRepository.save(oldUser);
    }

    @Override
    public void updateTaskOfUser(Long userId, Long taskId, Task task) {
        User user = getUserById(userId);

        boolean taskFound = false;
        for (Task t : user.getTasks()) {
            if (t.getId().equals(taskId)) {
                taskFound = true;
                break;
            }
        }
        if (!taskFound) {
            log.warn(WarnLoggingContext.getContext("User does not have this task").toString());
            throw new BusinessException(HttpStatus.NOT_FOUND, "User does not have this task");
        }
        taskService.updateTask(taskId, task);
    }

    @Override
    public boolean deleteUser(Long id) { // returns false if user not found
        if (!userExistsById(id)) {
            log.warn(WarnLoggingContext.getContext("User not found").toString());
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteTasksOfUser(Long id) {
        User user = getUserById(id);
        user.getTasks().clear();
        userRepository.save(user);
    }

    @Override
    public void deleteTaskOfUser(Long userId, Long taskId) {
        User user = getUserById(userId);
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                tasks.remove(task);
                userRepository.save(user);
            }
        }
        log.warn(WarnLoggingContext.getContext("Task not found").toString());
        throw new BusinessException(HttpStatus.NOT_FOUND, "Task not found");
    }

    public boolean userExistsById(Long id) {
        return userRepository.existsById(id);
    }

    public User getUserById(Long id) {
        Optional<?> responseFromDatabase = userRepository.findById(id); // This assignment saves us from calling the
                                                                        // database twice
        if (responseFromDatabase.isEmpty()) {
            log.warn(WarnLoggingContext.getContext("User not found").toString());
            throw new BusinessException(HttpStatus.NOT_FOUND, "User not found");
        }

        return (User) responseFromDatabase.get();
    }
}
