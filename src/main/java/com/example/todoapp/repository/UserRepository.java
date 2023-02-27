package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);// since email is unique we are using email to authenticate users

}
