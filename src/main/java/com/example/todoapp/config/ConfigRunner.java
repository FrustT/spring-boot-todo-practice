package com.example.todoapp.config;

import com.example.todoapp.task.Task;
import com.example.todoapp.task.TaskService;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRunner implements CommandLineRunner {

    TaskService taskService;
    UserService userService;

    public void addTestTasks(){
        Task task1 = Task.builder().title("First Title").description("Description").build();
        Task task2 = Task.builder().title("Second Title").description("2nd desc").completed(true).build();
        taskService.addTask(task1);
        taskService.addTask(task2);
    }

    public void addTestUsers(){
        User user1 = new User(null, "Burak", "burak@mail.com", "123456");
        User user2 = new User(null, "Onur", "onur@mail.com", "123456");
        userService.addUser(user1);
        userService.addUser(user2);
    }

    @Override
    public void run(String... args) throws Exception {
        addTestTasks();
        addTestUsers();
    }
}
