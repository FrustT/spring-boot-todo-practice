package user;

import java.util.List;

import com.example.todoapp.task.Task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private Long id;
    private String name;
    private String email;
    private String password;
    private List<Task> tasks;
}
