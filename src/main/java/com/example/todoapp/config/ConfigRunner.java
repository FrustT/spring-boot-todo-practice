package com.example.todoapp.config;

import com.example.todoapp.task.Task;
import com.example.todoapp.task.TaskService;
import com.example.todoapp.user.Role;
import com.example.todoapp.user.User;
import com.example.todoapp.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRunner implements CommandLineRunner {

    private TaskService taskService;
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder().name("Burak").email("burak@mail.com")
                .password("123456").role(Role.ADMIN).build();
        User user2 = User.builder().name("Onur").email("onur@mail.com")
                .password("123456").role(Role.ADMIN).build();

        userService.addUser(user1);
        userService.addUser(user2);
        Task task1 = Task.builder().title("First Title").description("Description").build();
        Task task2 = Task.builder().title("Second Title").description("2nd desc").completed(true).build();
        taskService.addTask(task1);
        taskService.addTask(task2);
        System.out.println("User 1 id: " + user1.getId());
        System.out.println("User 2 id: " + user2.getId());
        System.out.println("Task 1 id: " + task1.getId());
        System.out.println("Task 2 id: " + task2.getId());
        taskService.assignUserToTask(task1.getId(), user1.getId());
        taskService.assignUserToTask(task2.getId(), user2.getId());
        System.out.println("Users and Tasks are added");
    }
}
