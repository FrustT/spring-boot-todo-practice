package com.example.todoapp.config;

import com.example.todoapp.entity.Role;
import com.example.todoapp.entity.Task;
import com.example.todoapp.service.AuthenticationService;
import com.example.todoapp.entity.User;
import com.example.todoapp.model.requests.auth.RegisterRequest;
import com.example.todoapp.repository.UserRepository;
import com.example.todoapp.service.ITaskService;
import com.example.todoapp.service.IUserService;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConfigRunner implements CommandLineRunner {

        private ITaskService taskService;
        private IUserService userService;
        private Logger logger;
        private UserRepository userRepository;
        private AuthenticationService authenticationService;

        @Override
        public void run(String... args) throws Exception {
                RegisterRequest regRest1 = RegisterRequest.builder().name("Burak").email("burak@mail.com")
                                .password("123456")
                                .build();
                RegisterRequest regRest2 = RegisterRequest.builder().name("Onur").email("onur@mail.com")
                                .password("123456")
                                .build();

                authenticationService.register(regRest1);
                authenticationService.register(regRest2);

                User user1 = userRepository.findByEmail(regRest1.getEmail());
                User user2 = userRepository.findByEmail(regRest2.getEmail());

                userService.updateUser(user1.getId(), User.builder().name(user1.getName()).email(user1.getEmail())
                                .password(user1.getPassword()).role(Role.ADMIN).build());
                userService.updateUser(user2.getId(), User.builder().name(user2.getName()).email(user2.getEmail())
                                .password(user2.getPassword()).role(Role.USER).build());

                Task task1 = Task.builder().title("First Title").description("Description").build();
                Task task2 = Task.builder().title("Second Title").description("2nd desc").completed(true).build();
                taskService.addTask(task1);
                taskService.addTask(task2);
                logger.info("User 1 id: " + user1.getId());
                logger.info("User 2 id: " + user2.getId());
                logger.info("Task 1 id: " + task1.getId());
                logger.info("Task 2 id: " + task2.getId());
                taskService.assignUserToTask(task1.getId(), user1);
                taskService.assignUserToTask(task2.getId(), user2);
                logger.info("Users and Tasks are added");
        }
}
