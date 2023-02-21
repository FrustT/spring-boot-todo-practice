package user;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.todoapp.task.Task;

public interface IUserService {

    List<User> getUsers();

    User getUser(Long id);

    List<Task> getTasksOfUser(Long id);

    Task getTaskOfUser(Long userId, Long taskId);

    ResponseEntity<?> addTaskToUser(Long userId, Task task);

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(Long id, User user);

    ResponseEntity<?> updateTaskOfUser(Long userId, Long taskId, Task task);

    ResponseEntity<?> deleteUser(Long id);

    ResponseEntity<?> deleteTasksOfUser(Long id);

    ResponseEntity<?> deleteTaskOfUser(Long userId, Task task);

}
