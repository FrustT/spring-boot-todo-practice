package user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.todoapp.task.Task;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<Task> getTasksOfUser(Long id) {
        return getUser(id).getTasks();
    }

    @Override
    public Task getTaskOfUser(Long userId, Long taskId) {
        User user = getUser(userId);
        for (Task task : user.getTasks()) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    public ResponseEntity<?> addTaskToUser(Long userId, Task task) {
        User user = getUser(userId);
        user.getTasks().add(task);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        userRepository.findById(user.getId()).ifPresent(u -> {
            throw new RuntimeException("User already exists");
        });
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deleteTasksOfUser(Long id) {
        User user = getUser(id);
        user.getTasks().clear();
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        User u = getUser(id);
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setTasks(user.getTasks());
        userRepository.save(u);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> updateTaskOfUser(Long userId, Long taskId, Task task) {
        User user = getUser(userId);
        for (Task t : user.getTasks()) {
            if (t.getId().equals(taskId)) {
                t.setTitle(task.getTitle());
                t.setDescription(task.getDescription());
                t.setCompleted(task.isCompleted());
                t.setDueDate(task.getDueDate());
                t.setCompletedDate(task.getCompletedDate());
                userRepository.save(user);
                return ResponseEntity.ok().build();
            }
        }
        throw new RuntimeException("Task not found");
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deleteTaskOfUser(Long userId, Task task) {
        User user = getUser(userId);
        user.getTasks().remove(task);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

}
