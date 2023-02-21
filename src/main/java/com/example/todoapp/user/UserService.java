package com.example.todoapp.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    public ResponseEntity<?> addUser(User user) { // TODO unique field
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> updateUser(Long id, User user) {
        User u = getUser(id);
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        userRepository.save(u);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
