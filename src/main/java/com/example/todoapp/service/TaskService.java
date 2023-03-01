package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

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
    public Task addTask(Task task) { // TODO, instead of Task in parameter, use DTO for validation
        return taskRepository.save(task);
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    @Override
    public boolean deleteTask(Long id) { // TODO : should we use 404 and 200 or is just 200 enough?
        if (!taskRepository.existsById(id)) {
            return false;
        } // false = not found
        taskRepository.deleteById(id);
        return true;
    }

    @Override
    public Optional<Task> getTask(Long id) {
        Optional<Task> responseFromDatabase = taskRepository.findById(id);
        return responseFromDatabase;
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Optional<Task> responseFromDatabase = taskRepository.findById(id); // This assignment saves us from calling the
                                                                           // database twice

        if (responseFromDatabase.isEmpty())
            return null; // exit from method

        Task oldTask = (Task) responseFromDatabase.get();
        oldTask.setTitle(task.getTitle()); // TODO : what if user sends null? it will override existing data
        oldTask.setDescription(task.getDescription());
        oldTask.setCompleted(task.isCompleted());
        oldTask.setDueDate(task.getDueDate());
        oldTask.setCompletedDate(task.getCompletedDate());

        return taskRepository.save(oldTask);
    }

    @Override
    public Task assignUserToTask(Long taskId, Long userId) {
        Optional<Task> responseFromTaskDatabase = taskRepository.findById(taskId); // This assignment saves us from
                                                                                   // calling
                                                                                   // the database twice
        if (responseFromTaskDatabase.isEmpty())
            return null;
        Task task = responseFromTaskDatabase.get();

        Optional<User> responseFromUserDatabase = userRepository.findById(userId); // This assignment saves us from
                                                                                   // calling
                                                                                   // the database twice
        if (responseFromUserDatabase.isEmpty())
            return null;
        User user = responseFromUserDatabase.get();

        task.setOwner(user);
        taskRepository.save(task);
        return task;
    }

}
