package com.example.YemekhaneB.repository;

import com.example.YemekhaneB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<User,Long> {
    public User findByUsernameAndPassword(String username, String password);
    public User findByUsername(String username);
    Optional<User> findByusername(String username);



}
