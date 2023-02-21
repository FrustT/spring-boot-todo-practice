package user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.task.ITaskService;
import com.example.todoapp.task.Task;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {

    private IUserService userService;
    private ITaskService taskService;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksOfUser(@PathVariable("id") Long id) {
        return userService.getTasksOfUser(id);
    }

    @GetMapping("/{userId}/task/{taskId}")
    public Task getTaskOfUser(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        return userService.getTaskOfUser(userId, taskId);
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/{id}/task")
    public ResponseEntity<?> addTaskToUser(@PathVariable("userId") Long userId, @RequestBody Task task) {
        taskService.addTask(task);
        return userService.addTaskToUser(userId, task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        user.getTasks().forEach(task -> taskService.updateTask(task.getId(), task));
        return userService.updateUser(id, user);
    }

    @PutMapping("/{userId}/task/{taskId}")
    public ResponseEntity<?> updateTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long taskId, @RequestBody Task task) {
        taskService.updateTask(taskId, task);
        return userService.updateTaskOfUser(userId, taskId, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        taskService.deleteListOfTasks(userService.getTasksOfUser(id));
        return userService.deleteUser(id);
    }

    @DeleteMapping("/{userId}/task/{taskId}")
    public ResponseEntity<?> deleteTaskOfUser(@PathVariable("userId") Long userId,
            @PathVariable("taskId") Long TaskId) {
        Task task = taskService.getTask(TaskId);
        taskService.deleteTask(TaskId);
        return userService.deleteTaskOfUser(userId, task);
    }

    @DeleteMapping("/{id}/deletetasks")
    public ResponseEntity<?> deleteTasksOfUser(@PathVariable("id") Long id) {
        taskService.deleteListOfTasks(userService.getTasksOfUser(id));
        return userService.deleteTasksOfUser(id);
    }

}
