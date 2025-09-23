package com.stocksim.repository;

import com.stocksim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional; // <-- Use Optional

public interface UserRepository extends JpaRepository<User, Long> {
    // This now correctly returns an Optional
    Optional<User> findByUsername(String username);
}